package com.poc.mglApplication.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.mglApplication.integration.UcicIdFetchGateway;
import com.poc.mglApplication.model.BranchModel;
import com.poc.mglApplication.model.CreateLoanModel;
import com.poc.mglApplication.model.EmployeeModel;
import com.poc.mglApplication.model.UcicIdFetchModel;

@Service
public class MGLApplicationServiceImpl implements MGLApplicationService {

	@Autowired
	private UcicIdFetchGateway gateway;
	
	@Autowired
	@Qualifier("loanRequest")
	private MessageChannel loanRequest;

	@Override
	public String createLoan(CreateLoanModel loanModel) {
		EmployeeModel employeeModel = getEmployeeData();
		BranchModel branchModel = getBranchSpecificData();
		UcicIdFetchModel ucicIdFetchModel = new UcicIdFetchModel(branchModel.getMpNo(), branchModel.getFdglCode(), employeeModel.getEmpName(), 
				employeeModel.getEmailId(), employeeModel.getDesignation(), loanModel.getCustomerName(), loanModel.getCustomerDob(), 
				loanModel.getCustomerMobileNo(), loanModel.getCustomerIdNo());

		Message<String> gatewayResponse = gateway.fetchUcicId(MessageBuilder.withPayload(ucicIdFetchModel).build());

		return "Loan created for "+loanModel.getCustomerName().toUpperCase()+" having UCIC ID "+gatewayResponse.getPayload();
	}

	//	supposed to get data from db or session
	private BranchModel getBranchSpecificData() {
		return new BranchModel("BR001", "CODE");
	}

	//	supposed to get data from db or session
	private EmployeeModel getEmployeeData() {
		return new EmployeeModel("Sumesh", "sumesh@mf.com", "ccc");
	}

	@Autowired
	private RestTemplate rest;

	@Override
	public String createLoanUsingRest(CreateLoanModel loanModel) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<CreateLoanModel> entity = new HttpEntity<>(loanModel,headers);
		return rest.exchange("http://localhost:8082/api/v1/fetchUcic", HttpMethod.POST, entity, String.class).getBody();
	}

	@Override
	public String sendMsgToQueue(CreateLoanModel createLoanModel) {
		loanRequest.send(MessageBuilder.withPayload(createLoanModel).build());
		return "value send to the QUEUE!!";
	}

}
