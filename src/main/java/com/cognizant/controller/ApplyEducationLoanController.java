package com.cognizant.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

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

import com.cognizant.dao.ViewEducationLoanDao;
import com.cognizant.entity.ApplyEducationLoan;
import com.cognizant.entity.UserDetails;
import com.cognizant.exception.BankManagementException;
import com.cognizant.service.ApplyEducationLoanService;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@Controller
public class ApplyEducationLoanController {
	
	@Autowired
	ApplyEducationLoanService educationLoanService;
	@Autowired
	ViewEducationLoanDao viewEducationLoan;
	private List<ApplyEducationLoan> educationLoanList;
	@RequestMapping(value="/addEducationLoan", method=RequestMethod.GET)
	public String getEmployee(@ModelAttribute("loan") @Valid ApplyEducationLoan loan,
			BindingResult result, Model model, @RequestParam("msg") String msg){
		model.addAttribute("loan", new ApplyEducationLoan());
		model.addAttribute("msg", msg);
		return "ApplyEducationLoan";
	}
	@RequestMapping(value = "/addEducationLoan", method = RequestMethod.POST)
	public String addMember(@ModelAttribute("loan") @Valid ApplyEducationLoan loan,
			BindingResult result, 
			Model model, @RequestParam("msg") String msg){
		/*educationLoanList = new ArrayList<ApplyEducationLoan>();	
			educationLoanList.add(loan);*/
		
		//long accountNumber=1254785855236142l;
				long accountNumber=Long.parseLong(msg);
			System.out.println(loan);
			
			try{
				educationLoanService.insertEducationLoan(loan,accountNumber);
			
			}
			
		catch (ConstraintViolationException e) {
			
				e.printStackTrace();
				Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
				Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
				while(iterator.hasNext()){
					ConstraintViolation<?> next = iterator.next();
					System.out.println("Validation message: "+next.getMessage());
					System.out.println("Invalid field: "+next.getPropertyPath());
					System.out.println("Validation class/bean: "+next.getRootBean());
				 result.reject(next.getMessage());
					result.rejectValue(next.getPropertyPath().toString(), "", next.getMessage());
				
				}
		}	
			catch (BankManagementException e) {
				// TODO: handle exception
			//	boolean flag = false;
				//logger.error("Validation message: "+e.getMessage());
				String sb = e.getMessage();
				String sb1[] = sb.split(":");
				result.rejectValue(sb1[0].toString(), "",sb1[1].toString());
			}
			catch (Exception e) {
				// TODO: handle exception
				
				e.printStackTrace();
			}
			
			model.addAttribute("m1", loan.getEduLoanAccountNumber());
			if(result.hasErrors()){
				return "ApplyEducationLoan";
			}
			else{
		return "Output";
			}
	}
	
	
	}

