package tinhnv.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import tinhnv.dto.LoginForm;
import tinhnv.dto.RegisterForm;
import tinhnv.dto.account.AccountDTO;
import tinhnv.response.ErrorResponse;
import tinhnv.response.MyResponse;
import tinhnv.response.SuccessResponse;
import tinhnv.service.impl.AccountService;

@RestController
@Tag(name="Greeting", description="Đăng nhập/đăng ký tài khoản")
public class GreetingController {
	
	@Autowired
	AccountService accountService;

	@GetMapping("/login")
	@Operation(hidden=true)
	public ResponseEntity<MyResponse<LoginForm>> loginFirst() {
		return ResponseEntity.ok(
				new MyResponse<>(true,
						"Đăng nhập với 2 trường username và password",
						new LoginForm()
						)
				);
	}
	
	@GetMapping("/login_error")
	@Operation(hidden=true)
	public ResponseEntity<ErrorResponse> loginError(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String message = "";
		if(session != null) {
			AuthenticationException authException = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if(authException != null) message = authException.getMessage();
		}
		return ResponseEntity.badRequest().body(new ErrorResponse("Đăng nhập không thành công!", message, null));
	}
		
	@GetMapping("/welcome")
	@Operation(hidden=true)
	public ResponseEntity<List<Link>> welcome() {
		return ResponseEntity.ok(NationController.links(null));
	}
	
	@GetMapping("/register")
	@Operation(hidden=true)
	public ResponseEntity<SuccessResponse> registerIntro() {
		return ResponseEntity.accepted().body(
				new SuccessResponse("Đăng ký tài khoản", "Sử dụng phương thức POST", new RegisterForm())
				);
	}
	
	@PostMapping("/register")
	public EntityModel<AccountDTO> register(@RequestBody RegisterForm account) throws Exception {
		return EntityModel.of(accountService.createNewAccount(account), linkTo(methodOn(GreetingController.class).loginFirst())
				.withRel("login"));
	}
	
	@GetMapping("/bye")
	public ResponseEntity<String> logout() {
		return ResponseEntity.ok("Đăng xuất thành công");
	}
}
