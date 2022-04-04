package tinhnv.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import tinhnv.dto.LoginForm;
import tinhnv.dto.RegisterForm;
import tinhnv.dto.account.AccountDTO;
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
						"Login with your loginName and password.",
						new LoginForm()
						)
				);
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
				new SuccessResponse("how to register?", "POST to current link", new RegisterForm())
				);
	}
	
	@PostMapping("/register")
	public EntityModel<AccountDTO> register(@RequestBody RegisterForm account) throws Exception {
		return EntityModel.of(accountService.createNewAccount(account), linkTo(methodOn(GreetingController.class).loginFirst())
				.withRel("login"));
	}
}
