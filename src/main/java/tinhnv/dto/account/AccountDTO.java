package tinhnv.dto.account;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
}
