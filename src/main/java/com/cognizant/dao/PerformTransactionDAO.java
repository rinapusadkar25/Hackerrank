package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entity.TransactionDetails;
import com.cognizant.entity.UserDetails;
@Component
public class PerformTransactionDAO {
	
	@PersistenceContext
	private EntityManager em;
	@Transactional
	
	public void addUserDetails(UserDetails userdetails) {
		// TODO Auto-generated method stub
		System.out.println("before dao");
		em.persist(userdetails);
		System.out.println("after dao");
	}
	@PersistenceContext
	private EntityManager em1;
	
	public UserDetails updateTransactionDetails(long accountNumber) {
		// TODO Auto-generated method stub
		//em1.persist(accountNumber);
		UserDetails usd=em.find(UserDetails.class, accountNumber);
		return usd;
	}
	
	@Transactional
	public List<TransactionDetails> retriveTransactionDetails(long accountNumber) {
		// TODO Auto-generated method stub
	
		
		UserDetails retrive=em.find(UserDetails.class, accountNumber);
		/*System.out.println(retrive);
		System.out.println(retrive.getTransactionDetails());*/
		List<TransactionDetails> ls=new ArrayList<TransactionDetails>();
		
		ls= retrive.getTransactionDetails();
		System.out.println(ls);
		return ls;
		
		
	}

}
