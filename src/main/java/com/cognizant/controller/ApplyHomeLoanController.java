package com.cognizant.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.entity.ApplyHomeLoan;

import com.cognizant.entity.UserDetails;
import com.cognizant.exception.BankManagementException;
import com.cognizant.service.ApplyHomeLoanService;


@Controller
public class ApplyHomeLoanController {
	@Autowired
	ApplyHomeLoanService applyHomeLoanService;
	private UserDetails userDetails;
	private List<ApplyHomeLoan> homeLoans;
	private List<UserDetails> user;
	
	
	@RequestMapping(value = "/applyHomeLoan", method=RequestMethod.GET)
	public String getHomeLoan(@ModelAttribute("homeLoan")@Valid ApplyHomeLoan homeLoan,
			BindingResult result, Model model, @RequestParam("msg") String msg){
	
		model.addAttribute("homeLoan", new ApplyHomeLoan());
		model.addAttribute("msg", msg);
		return "applyHomeLoan";
	}
	

	@RequestMapping(value = "/applyHomeLoan", method = RequestMethod.POST)
	public String initiateHomeLoan(
		@ModelAttribute("homeLoan") @Valid ApplyHomeLoan homeLoan,
		BindingResult result, 
		Model model, @RequestParam("msg") String msg) {
		//Long account_number = 1254785855236142l;
				long account_number=Long.parseLong(msg);
		
		try {
			System.out.println("IIIIIn controlllllller");
			applyHomeLoanService.updateLoanDetails(account_number,homeLoan);
			
		} 
		catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
			while(iterator.hasNext()){
				ConstraintViolation<?> next = iterator.next();
				System.out.println("Validation message: "+next.getMessage());
				System.out.println("Invalid field: "+next.getPropertyPath());
				System.out.println("Validation class/bean: "+next.getRootBean());
//				 result.reject(next.getPropertyPath(), next.getMessage());
				result.rejectValue(next.getPropertyPath().toString(), "", next.getMessage());
//				result.rejectValue(arg0, arg1, arg2);
		
			}
		}
		
		catch(BankManagementException exp){
		
			String sb=exp.getMessage();
			String[] sb1=sb.split(":");
			result.rejectValue(sb1[0].toString(), "", sb1[1].toString());
	
			
		}
	
		catch (Exception e) {
		
			e.printStackTrace();
		}
		

		model.addAttribute("m1", homeLoan.getLoanAccountNumber());


	if (result.hasErrors()) {
		return "applyHomeLoan";
	} 
		
		return "successHomeLoan";

	}
}
