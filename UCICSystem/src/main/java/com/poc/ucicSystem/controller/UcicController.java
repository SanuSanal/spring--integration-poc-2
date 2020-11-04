package com.poc.ucicSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.ucicSystem.model.AuthResponseModel;
import com.poc.ucicSystem.model.AuthenticationInputModel;
import com.poc.ucicSystem.model.UcicFetchModel;
import com.poc.ucicSystem.service.UcicService;


@RestController
@RequestMapping("/api/v1")
public class UcicController {

	@Autowired
	private UcicService ucicService;
	
	@GetMapping("/")
	public String getServiceName() {
		return "UCICSystem";
	}
	
	@PostMapping("/authenticate")
	public AuthResponseModel authenticateTransaction(@RequestBody AuthenticationInputModel inputModel) {
		return new AuthResponseModel(true, "OK", ucicService.generateAuthToken(inputModel).toString(), "Auth token created");
	}
	
	@PostMapping("/fetchUcicId")
	public String fetchUcicId(@RequestBody UcicFetchModel model) {
		return ucicService.fetchUcicId(model);
	}
}
