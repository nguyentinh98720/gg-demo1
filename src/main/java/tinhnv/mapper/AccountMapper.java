package tinhnv.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import tinhnv.dto.account.AccountDTO;
import tinhnv.dto.account.DetailAccountDTO;
import tinhnv.entity.account.Account;

@Mapper(componentModel = "spring", disableSubMappingMethodsGeneration = true)
public abstract class AccountMapper {
	
	@BeforeMapping
	protected void setNote(Account entity, @MappingTarget DetailAccountDTO dto) {
		if(entity.isActive()) {
			dto.setNote("Trạng thái hoạt động bình thường");
		} else {
			dto.setNote("Tài khoản đang bị khóa!");
		}
	}
	
	@AfterMapping
	protected void hidePassword(@MappingTarget DetailAccountDTO dto) {
		dto.setPassword("*********");
	}

	/* 
	 * Note:
	 * Cần xác định tên rõ ràng cho mỗi phương thức
	 *  nếu có hai lớp thừa kế nhau
	 *  và chứa phương thức to list
	 * Ambiguous error
	 */
	
	@Named(value="dto")
	public abstract AccountDTO entityToDTO(Account entity);
	
	@IterableMapping(qualifiedByName = "dto")
	public abstract List<AccountDTO> entityToDTO(List<Account> entities);
	
	public abstract DetailAccountDTO entityToDetailDTO(Account entity);
}
