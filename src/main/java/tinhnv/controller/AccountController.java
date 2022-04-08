package tinhnv.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import tinhnv.dto.account.AccountDTO;
import tinhnv.dto.account.DetailAccountDTO;
import tinhnv.response.MyResponse;
import tinhnv.response.SuccessResponse;
import tinhnv.service.IAccountService;

@RestController
@Tag(name = "Account", description = "Quản lý tài khoản dành cho người dùng")
public class AccountController {
	
	@Autowired
	IAccountService service;

	@Operation(description="Xem thông tin tài khoản",
			responses= {
				@ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = DetailAccountDTO.class))), responseCode = "200")
			})
	@GetMapping("/profiles")
	public SuccessResponse profile(
			@Parameter(required=false, hidden=true) @AuthenticationPrincipal UserDetails user) {
		
		DetailAccountDTO account = service.detailAccount(user.getUsername());
		EntityModel<DetailAccountDTO> model = EntityModel.of(
				account,
				linkTo(methodOn(AccountController.class).profile(null)).withSelfRel(),
				linkTo(methodOn(AccountController.class).deleteProfile(null, null)).withRel("delete")
				);
		return new SuccessResponse("","Thông tin tài khoản", model);
	}
	
	
	@Operation(description="Chỉnh sửa thông tin tài khoản")
	@PutMapping("/profiles")
	public MyResponse<?> updateProfile(@Parameter(required=false, hidden=true) @AuthenticationPrincipal UserDetails user,
			@RequestBody DetailAccountDTO account) {
		
		if(!user.getUsername().equalsIgnoreCase(account.getLoginName())) return new MyResponse<>(false, "Chỉnh sửa thông tin thất bại!", null);
		
		EntityModel<AccountDTO> model = EntityModel.of(
				service.updateInformationForUser(account),
				linkTo(methodOn(AccountController.class).profile(null)).withRel("profile")
				);
		
		return new MyResponse<>(true,"Chỉnh sửa thông tin thành công.", model);
	}
	
	
	@Operation(description="Xóa tài khoản")
	@DeleteMapping("/deleteAccount")
	public MyResponse<String> deleteProfile(
			@Parameter(required=false, hidden=true) @AuthenticationPrincipal UserDetails user,
			@RequestParam(name="loginName", defaultValue="") String loginName) {
		
		if(loginName.length() != 0 && loginName.equals(user.getUsername())) {
			service.deleteAccount(loginName);
			return new MyResponse<>(true, "", "Đã xóa tài khoản.");
		}
		
		else return new MyResponse<>(false, "", "Không thể xóa tài khoản!");
	}
}
