package com.cognizant.controller;

import java.util.*;

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

import com.cognizant.entity.TransactionDetails;
import com.cognizant.exception.TransactionTypeExceptions;
import com.cognizant.service.PerformTransactionService;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@Controller
public class PerformTransactionController {

	@Autowired
	PerformTransactionService performtransactionservice;



	@RequestMapping(value="/performtransaction", method=RequestMethod.GET)
	public String getTransactionDetails(@ModelAttribute("Transaction") @Valid TransactionDetails Transaction,
			BindingResult result, Model model, @RequestParam("msg") String msg){
		model.addAttribute("Transaction", new TransactionDetails());
		model.addAttribute("msg", msg);
		return "PerformTransaction";

	}

	@RequestMapping(value="/performtransaction", method=RequestMethod.POST)
	public String performTransaction(@ModelAttribute("Transaction") @Valid TransactionDetails Transaction,
			BindingResult result, Model model,  @RequestParam("msg") String msg){
		//long accountNumber=1254785855236142l;
		long accountNumber=Long.parseLong(msg);


		try {
			performtransactionservice.updateTransactionDetails(Transaction, accountNumber);
			
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
			while(iterator.hasNext()){
				ConstraintViolation<?> next = iterator.next();
				System.out.println("Validation message: "+next.getMessage());
				System.out.println("Invalid field: "+next.getPropertyPath());
				System.out.println("Validation class/bean: "+next.getRootBean());
				result.rejectValue(next.getPropertyPath().toString(), "", next.getMessage());
			}

		}
		catch (TransactionTypeExceptions e) {
			// TODO: handle exception
			String sb=e.getMessage();
			String sb1[]=sb.split(":");
			result.rejectValue(sb1[0].toString(),"",sb1[1].toString());
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("m1", Transaction.getTransactionId());
		
			if(result.hasErrors()){
				return "PerformTransaction";
			}
			else{
				model.addAttribute("m2", Transaction.getUserDetails().getAccountBalance());
				return "SuccessTransaction";
			}
		}

	
}