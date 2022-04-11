package tinhnv.service;

import java.util.List;

import tinhnv.dto.RegisterForm;
import tinhnv.dto.account.AccountDTO;
import tinhnv.dto.account.DetailAccountDTO;

public interface IAccountService {

	AccountDTO createNewAccount(DetailAccountDTO account);
	List<AccountDTO> allAccount(int pageNumber, int pageSize, boolean deleted);
	List<AccountDTO> allAccount(boolean deleted);
	AccountDTO detailAccount(Long id);
	AccountDTO updateInformation(DetailAccountDTO account);
	
	//for admin
	void deleteAccount(Long id);
	
	//for user
	void deleteAccount(String loginName);
	DetailAccountDTO detailAccount(String loginName);
	AccountDTO createNewAccount(RegisterForm account);
	AccountDTO updateInformationForUser(DetailAccountDTO account);
}
