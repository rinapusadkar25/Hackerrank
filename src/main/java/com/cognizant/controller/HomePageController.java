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

import com.cognizant.dao.ViewEducationLoanDao;
import com.cognizant.entity.ApplyEducationLoan;
import com.cognizant.entity.UserDetails;

@Controller
public class HomePageController {
	@Autowired
	ViewEducationLoanDao viewEducationLoan;
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHomePage(@RequestParam("accountNumber") String acNo, Model model){
	System.out.println(acNo);
	model.addAttribute("msg",acNo);
	return "Home";
}
@RequestMapping(value="/homepage",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
public @ResponseBody  List<UserDetails> getList(@RequestParam("accountNumber") String msg,Model model){
	
/*long accountNumber=1254785855236142l;*/
	model.addAttribute("msg", msg);
	System.out.println("in cont"+msg);
	long accountNumber=Long.parseLong(msg);
	UserDetails userDetails=viewEducationLoan.findEducationLoanDetails(accountNumber);
	List<UserDetails> List=new ArrayList<UserDetails>();
userDetails.setApply(null);
userDetails.setHomeLoan(null);
userDetails.setTransactionDetails(null);

List.add(userDetails);

	
	
	
	return List;
}


}
