package tinhnv.entity.account.builder;

import tinhnv.dto.account.AccountDTO;
import tinhnv.dto.account.DetailAccountDTO;
import tinhnv.entity.account.Account;

public interface AccountBuilder {

	AccountBuilder setId(Long id);
	AccountBuilder setLoginName(String loginName);
	AccountBuilder setPassword(String password);
	AccountBuilder setFullName(String fullName);
	AccountBuilder setAddress(String address);
	AccountBuilder setActive(boolean isActive);
	AccountBuilder setRole(String role);
	Account buildEntity();
	AccountDTO buildDTO();
	DetailAccountDTO buildDetailDTO();
}
