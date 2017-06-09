package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entity.ApplyHomeLoan;
import com.cognizant.entity.UserDetails;


@Component
public class ViewHomeLoanDAO {

	@PersistenceContext
	private EntityManager em;



	@Transactional
		public UserDetails retrieveLoanDetails(long ACCOUNT_NUMBER) {
		
	
		
		System.out.println("IN DAO");
		UserDetails ud=em.find(UserDetails.class,ACCOUNT_NUMBER);
		System.out.println("551521251455555555555555555555555555"+ud);
	/*List<ApplyHomeLoan>homeloans=userDetails.getHomeLoan();*/
	
		//System.out.println(userDetails.getHomeLoan());
	/*	System.out.println(userDetails);
		List<ApplyHomeLoan> Details=new ArrayList<>();
		ApplyHomeLoan loanDetails=em.find(ApplyHomeLoan.class, ACCOUNT_NUMBER);
		Details.add(loanDetails);
		System.out.println(loanDetails);
		//loanDetails=userDetails.getHomeLoan();
		userDetails.setHomeLoan(Details);
		System.out.println(userDetails);*/
		/*System.out.println(userDetails);
		List<ApplyHomeLoan> loanDetails=new ArrayList<>();
		
		loanDetails=userDetails.getHomeLoan();
		System.out.println("AFTER DAO");
		//System.out.println(userDetails.getHomeLoan());
//	ApplyHomeLoan homeLoan =em.find(ApplyHomeLoan.class, 2);
*/		
		System.out.println("after dao");

		return ud;
}

}
