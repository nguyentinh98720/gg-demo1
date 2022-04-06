package tinhnv.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import tinhnv.entity.account.Account;
import tinhnv.entity.account.builder.AccountBuilder;
import tinhnv.entity.account.concreate.AccountConcreateBuilder;

@Schema(name = "Chi tiết tài khoản")
public class DetailAccountDTO extends AccountDTO {

	@Schema(description = "Mật khẩu đăng nhập của tài khoản")
	private String password;
	@Schema(description = "Địa chỉ")
	private String address;
	
	public DetailAccountDTO() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static DetailAccountDTO toDTO(Account account) {
		AccountBuilder builder = new AccountConcreateBuilder();
		return builder.setId(account.getId())
				.setLoginName(account.getLoginName())
				.setPassword(account.getPassword())
				.setFullName(account.getFullName())
				.setAddress(account.getAddress())
				.setActive(account.isActive())
				.setRole(account.getRole())
				.buildDetailDTO();
	}
}
