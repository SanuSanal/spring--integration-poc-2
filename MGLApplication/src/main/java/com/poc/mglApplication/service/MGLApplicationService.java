package com.poc.mglApplication.service;

import com.poc.mglApplication.model.CreateLoanModel;

public interface MGLApplicationService {

	public String createLoan(CreateLoanModel loanModel);
	
	public String createLoanUsingRest(CreateLoanModel loanModel);

	public String sendMsgToQueue(CreateLoanModel createLoanModel);
}
