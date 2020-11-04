package com.poc.ucicSystem.service;

import com.poc.ucicSystem.model.AuthenticationInputModel;
import com.poc.ucicSystem.model.UcicFetchModel;

public interface UcicService {
	
	public Integer generateAuthToken(AuthenticationInputModel inputModel);

	public String fetchUcicId(UcicFetchModel model);
}
