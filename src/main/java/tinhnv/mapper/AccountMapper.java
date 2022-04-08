package tinhnv.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import tinhnv.dto.account.AccountDTO;
import tinhnv.dto.account.DetailAccountDTO;
import tinhnv.entity.account.Account;

@Mapper(componentModel = "spring", disableSubMappingMethodsGeneration = true)
public interface AccountMapper {

	/* 
	 * Note:
	 * Cần xác định tên rõ ràng cho mỗi phương thức
	 *  nếu có hai lớp thừa kế nhau
	 *  và chứa phương thức to list
	 * Ambiguous error
	 */
	
	@Named(value="dto")
	AccountDTO entityToDTO(Account entity);
	
	@IterableMapping(qualifiedByName = "dto")
	List<AccountDTO> entityToDTO(List<Account> entities);
	
	DetailAccountDTO entityToDetailDTO(Account entity);
}
