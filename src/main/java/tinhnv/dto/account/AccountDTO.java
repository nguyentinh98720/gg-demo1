package tinhnv.dto.account;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import tinhnv.entity.account.Account;
import tinhnv.entity.account.builder.AccountBuilder;
import tinhnv.entity.account.concreate.AccountConcreateBuilder;

@Component
public class AccountDTO {

	@Schema(description = "Mã tài khoản được tạo tự động bởi database")
	private Long id;
	@Schema(description = "Tên đăng nhập")
	private String loginName;
	@Schema(description = "Tên đầy đủ của chủ tài khoản")
	private String fullName;
	@Schema(description = "Quyền của tài khoản khi truy cập ứng dụng")
	private String role;
	@Schema(description = "Tài khoản bị khóa hay đang hoạt động")
	private boolean active;
	
	public static AccountDTO toDTO(Account account) {
		AccountBuilder accountBuilder = new AccountConcreateBuilder();
		accountBuilder.setId(account.getId())
		.setLoginName(account.getLoginName())
		.setFullName(account.getFullName())
		.setRole(account.getRole())
		.setActive(account.isActive());
		return accountBuilder.buildDTO();
	}

	public AccountDTO() {
		super();
	}

	public AccountDTO(Long id, String loginName, String fullName, String role, boolean active) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.fullName = fullName;
		this.role = role;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
