package tinhnv.entity.account.concreate;

import tinhnv.dto.account.AccountDTO;
import tinhnv.dto.account.DetailAccountDTO;
import tinhnv.entity.account.Account;
import tinhnv.entity.account.builder.AccountBuilder;

public class AccountConcreateBuilder implements AccountBuilder {

	private Long id;
	private String loginName;
	private String password;
	private String fullName;
	private String address;
	private boolean active;
	private String role;

	@Override
	public AccountBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	@Override
	public AccountBuilder setLoginName(String loginName) {
		this.loginName = loginName;
		return this;
	}

	@Override
	public AccountBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public AccountBuilder setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	@Override
	public AccountBuilder setAddress(String address) {
		this.address = address;
		return this;
	}

	@Override
	public AccountBuilder setActive(boolean isActive) {
		this.active = isActive;
		return this;
	}

	@Override
	public AccountBuilder setRole(String role) {
		this.role = role;
		return this;
	}

	@Override
	public Account buildEntity() {
		return new Account(loginName, fullName, address, active, role);
	}

	@Override
	public AccountDTO buildDTO() {
		return new AccountDTO(id, loginName, fullName, role, active);
	}

	@Override
	public DetailAccountDTO buildDetailDTO() {
		DetailAccountDTO dto = new DetailAccountDTO();
		dto.setId(id);
		dto.setLoginName(loginName);
		dto.setPassword(password);
		dto.setFullName(fullName);
		dto.setActive(active);
		dto.setAddress(address);
		dto.setRole(role);
		return dto;
	}
}
