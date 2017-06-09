package com.cognizant.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.cognizant.service.PerformTransactionService;
@Controller
public class ViewTransactionController {

	@Autowired
	PerformTransactionService performtransactionservice;
	
	@RequestMapping(value="/viewtransaction", method=RequestMethod.GET)
	public  String getList(Model model,  @RequestParam("msg") String msg)
	{
		model.addAttribute("msg", msg);
	return "ViewTransactionDetails";
}
	
	
	@RequestMapping(value="/viewtransaction1", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TransactionDetails> List(Model model,  @RequestParam("msg") String msg)
	{
		//long accountNumber=1254785855236142l;
				long accountNumber=Long.parseLong(msg);
	//performtransactionservice.retriveTransactionDetails(accountNumber);
	System.out.println(performtransactionservice.retriveTransactionDetails(accountNumber));
	List<TransactionDetails> t=new ArrayList<TransactionDetails>();	
	t=performtransactionservice.retriveTransactionDetails(accountNumber);
	/*List<TransactionDetails> t1=new ArrayList<TransactionDetails>();
	t1=performtransactionservice.retriveTransactionDetails(accountNumber);
	for(int i=0;i<t1.size();i++){
		TransactionDetails tobj=null;
	tobj=t1.get(i);
	tobj.setUserdetails(null);
	t.add(tobj);
	}*/
	System.out.println("Controller"+t);
	return t;
}
}
