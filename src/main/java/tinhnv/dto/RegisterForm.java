package tinhnv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
}
