package tinhnv.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Form đăng nhập")
public class LoginForm {

	@Schema(description="Tên đăng nhập")
	private String loginName;
	@Schema(description="Mật khẩu đăng nhập")
	private String password;
	
	public LoginForm() {}
	
	public LoginForm(String loginName, String password) {
		this.loginName = loginName;
		this.password = password;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
