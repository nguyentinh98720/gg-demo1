package tinhnv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import tinhnv.assembler.account.AccountAssembler;
import tinhnv.assembler.account.CollectionAccountDTOAssembler;
import tinhnv.dto.account.AccountDTO;
import tinhnv.dto.account.DetailAccountDTO;
import tinhnv.response.MyResponse;
import tinhnv.service.IAccountService;

@Tag(name = "Account Manage" ,description = "Quản lý tài khoản ứng dụng cho người quản trị")
@RestController
@RequestMapping("/account-manage")
public class AccountManageController {

	@Autowired
	IAccountService service;
	@Autowired
	AccountAssembler modelAssembler;
	@Autowired
	CollectionAccountDTOAssembler collectionAssembler;
	
	@GetMapping("/accounts")
	@Operation(description="Lấy danh sách tất cả các tài khoản")
	public MyResponse<CollectionModel<EntityModel<AccountDTO>>> all() {
		return new MyResponse<>(true, "", collectionAssembler.toModel(service.allAccount(false)));
	}
	
	@GetMapping("/accounts/{pageNo}/{pageSize}")
	@Operation(description="Lấy danh sách theo số trang")
	public MyResponse<CollectionModel<EntityModel<AccountDTO>>> list(
			@Parameter(required=true, description="Số thứ tự của trang") @PathVariable("pageNo") Integer pageNo,
			@Parameter(required=true, description="Số phần tử trên mỗi trang") @PathVariable("pageSize") Integer pageSize) {
		return new MyResponse<>(true, "", collectionAssembler.toModel(service.allAccount(pageNo, pageSize, false)));
	}
	
	@GetMapping("/accounts/{id}")
	@Operation(description="Lấy thông tin chi tiết tài khoản")
	public MyResponse<EntityModel<AccountDTO>> one(
			@Parameter(required=true, description="Mã id của tài khoản cần lấy thông tin") @PathVariable("id") Long id) {
		return new MyResponse<>(true, "",
				modelAssembler.toModel(service.detailAccount(id)));
	}
	
	@PostMapping("/accounts")
	@Operation(description="Thêm tài khoản mới")
	public MyResponse<EntityModel<AccountDTO>> create(
			@Parameter(required=true, description="Thông tin tài khoản mới") @RequestBody DetailAccountDTO account) throws Exception {
		AccountDTO acc = service.createNewAccount(account);
		return new MyResponse<>(true, "", modelAssembler.toModel(acc));
	}
	
	@PutMapping("/accounts")
	@Operation(description="Chỉnh sửa thông tin tài khoản")
	public MyResponse<EntityModel<AccountDTO>> update(
			@Parameter(required=true, description="Thông tin cập nhật") @RequestBody DetailAccountDTO account) {
		return new MyResponse<>(true, "",
				modelAssembler.toModel(service.updateInformation(account)));
	}
	
	@DeleteMapping("/accounts/{id}")
	@Operation(description="Xóa tài khoản")
	public MyResponse<Integer> delete(
			@Parameter(required=true, description="Mã id của tài khoản cần xóa") @PathVariable Long id) {
		service.deleteAccount(id);
		return new MyResponse<>(true, "", null);
	}
	
	
}
