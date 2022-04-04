package tinhnv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tinhnv.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
//	@Modifying
//	@Query("update Account as a set a.deleted = true where a.loginName = ?1")
//	int deleteAccount(String loginName);
//	
//	@Modifying
//	@Query("update Account as a set a.deleted = true where a.id = ?1")
//	int deleteAccount(Long id);
	
	void deleteByLoginName(String loginName);
	
	Optional<Account> findByLoginName(String loginName);
	
	@Query("select count(a) from Account as a where a.loginName = ?1")
	Integer isLoginNameExist(String loginName);
}
