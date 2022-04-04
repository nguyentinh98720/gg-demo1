package tinhnv.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Form đăng ký")
public class RegisterForm {

	@Schema(description="Tên đăng nhập")
	private String loginName;
	@Schema(description="Mật khẩu")
	private String password;
	@Schema(description="Nhập lại mật khẩu")
	private String repeatPass;
	@Schema(description="Họ và tên")
	private String fullName;
	@Schema(description="Địa chỉ")
	private String address;

	public RegisterForm() {
		super();
	}

	public RegisterForm(String loginName, String password, String repeatPass, String fullName, String address) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.repeatPass = repeatPass;
		this.fullName = fullName;
		this.address = address;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public String getRepeatPass() {
		return repeatPass;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
