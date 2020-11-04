package com.poc.mglApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.mglApplication.model.CreateLoanModel;
import com.poc.mglApplication.service.MGLApplicationService;

@RestController
@RequestMapping("/api/v1")
public class MGLController {
	
	@Autowired
	private MGLApplicationService service;
	
	@GetMapping("/")
	public String getServiceName() {
		return "MGLApplication";
	}
	
	@PostMapping("/createloan")
	public String createLoanForCustomer(@RequestBody CreateLoanModel createLoanModel) {
		return service.createLoan(createLoanModel);
	}
	
	@PostMapping("/createloanrest")
	public String createLoanForCustomerUsingRest(@RequestBody CreateLoanModel createLoanModel) {
		return service.createLoanUsingRest(createLoanModel);
	}
	
	@PostMapping("/sendmessagetoqueue")
	public String sendMsgToQueue(@RequestBody CreateLoanModel createLoanModel) {
		return service.sendMsgToQueue(createLoanModel);
	}
}
