package tinhnv.assembler.account;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import tinhnv.controller.AccountManageController;
import tinhnv.dto.account.AccountDTO;

@Component
public class AccountAssembler implements RepresentationModelAssembler<AccountDTO, EntityModel<AccountDTO>> {

	@Override
	public EntityModel<AccountDTO> toModel(AccountDTO entity) {
		return EntityModel.of(entity,
				linkTo(methodOn(AccountManageController.class).all()).withRel("all"),
				linkTo(methodOn(AccountManageController.class).one(entity.getId())).withSelfRel()
				);
	}

}
