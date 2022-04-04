package tinhnv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tinhnv.dto.RegisterForm;
import tinhnv.dto.account.AccountDTO;
import tinhnv.dto.account.DetailAccountDTO;
import tinhnv.entity.account.Account;
import tinhnv.exception.AccountNotFoundException;
import tinhnv.repository.AccountRepository;
import tinhnv.service.IAccountService;

@Service
@Transactional
public class AccountService implements IAccountService {
	
	@Autowired
	AccountRepository repository;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public AccountDTO createNewAccount(DetailAccountDTO account) {
		Account newAccount = new Account(account.getLoginName(),
				account.getFullName(), account.getAddress(),
				account.isActive(), account.getRole());
		newAccount.setPassword(passwordEncoder.encode(account.getPassword()));
		return AccountDTO.toDTO(repository.save(newAccount));
	}

	@Override
	public List<AccountDTO> allAccount(int pageNumber, int pageSize, boolean deleted) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Account> accounts = new ArrayList<>();
		findAll(deleted, page).forEach(accounts::add);
		return accounts.stream().map(AccountDTO::toDTO).collect(Collectors.toList());
	}

	@Override
	public AccountDTO detailAccount(Long id) {
		Account account = repository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException("No Accound found for detail!"));
		return DetailAccountDTO.toDTO(account);
	}

	@Override
	public List<AccountDTO> allAccount(boolean deleted) {
		List<Account> accounts = new ArrayList<>();
		findAll(deleted, null).forEach(accounts::add);
		return accounts.stream().map(AccountDTO::toDTO).collect(Collectors.toList());
	}

	@Override
	public AccountDTO updateInformation(DetailAccountDTO account) {
		Optional<Account> updateAccount = repository.findById(account.getId());
		if(updateAccount.isPresent()) {
			Account update = updateAccount.get();
			update.setFullName(account.getFullName());
			update.setRole(account.getRole());
			update.setActive(account.isActive());
			update.setPassword(passwordEncoder.encode(account.getPassword()));
			return AccountDTO.toDTO(repository.save(update));
		}
		throw new AccountNotFoundException("No Account found to update!");
	}

	@Override
	public DetailAccountDTO detailAccount(String loginName) {
		Account account = repository.findByLoginName(loginName)
				.orElseThrow(() -> new AccountNotFoundException("No account found with login name: " + loginName));
		return DetailAccountDTO.toDTO(account);
	}

	@Override
	public void deleteAccount(String loginName) {
		repository.deleteByLoginName(loginName);
	}

	@Override
	public void deleteAccount(Long id) {
		repository.deleteById(id);
	}

	@Override
	public AccountDTO createNewAccount(RegisterForm account) throws Exception {
		if(repository.isLoginNameExist(account.getLoginName()) > 0) throw new Exception("Account  already exist!");
		if(!account.getPassword().equals(account.getRepeatPass())) throw new Exception("Password and repeat password do not match!");
		
		Account createAccount = new Account(
				account.getLoginName(), account.getFullName(), account.getAddress(),
				true, "FREE_USER"
				);
		createAccount.setPassword(passwordEncoder.encode(account.getPassword()));
		return AccountDTO.toDTO(repository.save(createAccount));
	}
	
	private Iterable<Account> findAll(boolean deleted, Pageable page) {
		Session session = entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedAccountFilter");
		filter.setParameter("isDeleted", deleted);
		Iterable<Account> accounts = page!=null?repository.findAll(page):repository.findAll();
		session.disableFilter("deletedAccountFilter");
		return accounts;
	}

	@Override
	public AccountDTO updateInformationForUser(DetailAccountDTO account) {
		Optional<Account> updateAccount = repository.findById(account.getId());
		if(updateAccount.isPresent()) {
			Account update = updateAccount.get();
			update.setFullName(account.getFullName());
			update.setPassword(passwordEncoder.encode(account.getPassword()));
			return AccountDTO.toDTO(repository.save(update));
		}
		throw new AccountNotFoundException("No Account found to update!");
	}
}
