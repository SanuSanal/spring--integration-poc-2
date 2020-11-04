package com.poc.ucicSystem.service;

import org.springframework.stereotype.Service;

import com.poc.ucicSystem.model.AuthenticationInputModel;
import com.poc.ucicSystem.model.UcicFetchModel;

@Service
public class UcicServiceImpl implements UcicService {

	@Override
	public Integer generateAuthToken(AuthenticationInputModel inputModel) {
		String authString = inputModel.getAppUserName()+inputModel.getAppPass()+inputModel.getMpNo()+inputModel.getFdglCode()+inputModel.getEmpName();
		Integer authToken = authString.hashCode();
		System.out.println("TOKEN CREATED");
		return Math.abs(authToken);
	}

	@Override
	public String fetchUcicId(UcicFetchModel model) {
		System.out.println("UCIC returned");
		if (model.getAuth_Code() == null || "".equals(model.getAuth_Code())) {
			return "Missing or Invalid Auth token";
		} else if (model.getIdNo().equals("1234")) {
			return "UCIC001234";
		} else {
			return "UCIC000788";
		}
	}

}
