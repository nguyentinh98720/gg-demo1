package tinhnv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tinhnv.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByLoginName(String loginName);
	
	@Query("select count(a) from Account as a where a.loginName = ?1")
	Integer isLoginNameExist(String loginName);
}
