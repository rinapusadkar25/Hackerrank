package com.cognizant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.dao.ApplyHomeLoanDAO;
import com.cognizant.entity.ApplyHomeLoan;
import com.cognizant.entity.UserDetails;
import com.cognizant.exception.BankManagementException;

@Component
public class ApplyHomeLoanService {

	@Autowired
	private ApplyHomeLoanDAO homeLoanDao;

	public void insertHomeLoanDetails(List<ApplyHomeLoan> homeLoan, UserDetails userDetails) throws Exception {

		for (ApplyHomeLoan homeLoan2 : homeLoan) {
			homeLoan2.setLoanAccountNumber(generateLoanAccNumber());
			homeLoan2.setHomeLoanId(generateHomeLoanId(userDetails.getAccountNumber()));
		}
		userDetails.setHomeLoan(homeLoan);
		homeLoanDao.insertHomeLoanDetails(userDetails);

	}

	public String generateHomeLoanId(long accountNumber) {

		System.out.println(accountNumber);

		StringBuffer sb = new StringBuffer();
		String s1 = Long.toString(accountNumber);

		System.out.println(s1);

		sb.append(s1);
		String lan = sb.toString();
		System.out.println(lan);
		String idNumberLast = lan.substring(13, 16);
		System.out.println(idNumberLast);
		String homeLoanId = "HL-".concat(idNumberLast);

		return homeLoanId;

	}

	public long generateLoanAccNumber() {
		Long num = (long) (Math.random() * 10000000000000000L);
		return num;
	}

	@Transactional
	public void updateLoanDetails(Long accountNumber, ApplyHomeLoan h2) throws BankManagementException {
		
		

		if (h2.getLoanDuration() == 5 || h2.getLoanDuration() == 10 || h2.getLoanDuration() == 15
				|| h2.getLoanDuration() == 20) {

			long income = h2.getAnnualIncome();
			long loanAmount = h2.getLoanAmount();
			if (income != (.10 * loanAmount)) {
				throw new BankManagementException("loanAmount:Please enter valid amount.");

			}

			System.out.println("in service");
			homeLoanDao.updateLoanDetails(accountNumber);
			UserDetails userDetails = homeLoanDao.updateLoanDetails(accountNumber);
			h2.setUserDetails(userDetails);

			String id = generateHomeLoanId(userDetails.getAccountNumber());
			h2.setHomeLoanId(id);
			h2.setLoanAccountNumber(generateLoanAccNumber());

			List<ApplyHomeLoan> hLoan = new ArrayList<ApplyHomeLoan>();
			hLoan.add(h2);
			System.out.println(hLoan);
			userDetails.setHomeLoan(hLoan);

		} else {
			
			throw new BankManagementException("loanDuration:Loan Duration must be either 5, 10, 15 or 20 years");
		}

	}

}
