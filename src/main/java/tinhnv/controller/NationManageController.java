package tinhnv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinhnv.service.INationService;

@RestController
@RequestMapping("/admin")
public class NationManageController {

	@Autowired
	INationService service;
}
