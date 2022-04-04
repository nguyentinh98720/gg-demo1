package tinhnv.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tinhnv.entity.account.Account;
import tinhnv.repository.AccountRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	AccountRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = repository.findByLoginName(username);
		if(account.isPresent()) return new MyUserDetails(account.get());
		throw new UsernameNotFoundException("Account with login name: '" + username + "' doesn't exist!");
	}

}
