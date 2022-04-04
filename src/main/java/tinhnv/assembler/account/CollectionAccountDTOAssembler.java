package tinhnv.assembler.account;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import tinhnv.controller.AccountManageController;
import tinhnv.dto.account.AccountDTO;

@Component
public class CollectionAccountDTOAssembler implements RepresentationModelAssembler<List<AccountDTO>, CollectionModel<EntityModel<AccountDTO>>>{

	@Autowired
	AccountAssembler assembler;
	
	@Override
	public CollectionModel<EntityModel<AccountDTO>> toModel(List<AccountDTO> entities) {
		List<EntityModel<AccountDTO>> list = entities.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(
				list,
				linkTo(methodOn(AccountManageController.class).list(null, null)).withRel("page"),
				linkTo(methodOn(AccountManageController.class).all()).withRel("all"),
				linkTo(methodOn(AccountManageController.class).one(null)).withRel("one")
				);
	}
}
