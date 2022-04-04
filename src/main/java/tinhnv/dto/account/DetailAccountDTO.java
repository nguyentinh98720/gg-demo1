package tinhnv.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import tinhnv.entity.account.Account;

@Schema(name = "Chi tiết tài khoản")
public class DetailAccountDTO extends AccountDTO {

	@Schema(description = "Mật khẩu đăng nhập của tài khoản")
	private String password;
	@Schema(description = "Địa chỉ")
	private String address;

	public DetailAccountDTO(Long id, String loginName, String password, String fullName,
			String address, boolean active,
			String role) {
		super(id, loginName, fullName, role, active);
		this.password = password;
		this.address = address;
	}
	
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
		return new DetailAccountDTO(account.getId(), account.getLoginName(),
				account.getPassword(), account.getFullName(), account.getAddress(),
				account.isActive(), account.getRole());
	}
}
