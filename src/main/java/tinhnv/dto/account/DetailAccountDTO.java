package tinhnv.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "Chi tiết tài khoản")
@Getter @Setter @NoArgsConstructor
public class DetailAccountDTO extends AccountDTO {

	@Schema(description = "Mật khẩu đăng nhập của tài khoản")
	private String password;
	@Schema(description = "Địa chỉ")
	private String address;
	
	@Schema(hidden=true)
	private String note;
}
