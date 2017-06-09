package com.cognizant.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="TRANSACTION_DETAILS")
public class TransactionDetails {
	@Id
	@Column(name="TRANSACTION_ID")
	private Long  transactionId;
	
	@NotEmpty(message="Mandatory Field.")
	@Pattern(regexp="^[a-zA-Z\b \b]*$", message = "Only Alphabets are allowed.")
	@Column(name="TRANSACTION_DESCRIPTION")
	private String transactiondescription;
	
	
	@NotEmpty(message="Mandatory Field")
	@Column(name="TRANSACTION_TYPE")
	private String transactiontype;
	
	@javax.validation.constraints.NotNull(message="Mandatory Field")
	@Range(min=1,max=999999999999999l , message="Please enter valid amount.")
	@Column(name="TRANSACTION_AMOUNT")
	private Double transactionAmount;
	
	
	
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	@JsonBackReference 
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ACCOUNT_NUMBER")
	private UserDetails userDetails;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactiondescription() {
		return transactiondescription;
	}
	@Override
	public String toString() {
		return "TransactionDetails [transactionId=" + transactionId + ", transactiondescription="
				+ transactiondescription +  ", transactiontype=" + transactiontype
				+ ", transactionAmount=" + transactionAmount + "]";
	}
	public void setTransactiondescription(String transactiondescription) {
		this.transactiondescription = transactiondescription;
	}
	
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public TransactionDetails(){
		
	}
	public TransactionDetails(String transactionId, String transactiondescription, 
			String transactiontype, Double transactionAmount, UserDetails userDetails) {
		super();
		this.userDetails= userDetails;
		/*this.transactionId = transactionId;*/
		this.transactiondescription = transactiondescription;
		//this.accountNumber = accountNumber;
		this.transactiontype = transactiontype;
		this.transactionAmount = transactionAmount;
	}
	public TransactionDetails(String transactionId, String transactiondescription, 
			String transactiontype, Double transactionAmount) {
		super();
		/*this.transactionId = transactionId;*/
		this.transactiondescription = transactiondescription;
		
		this.transactiontype = transactiontype;
		this.transactionAmount = transactionAmount;
		
	}
	public TransactionDetails( String transactiondescription, 
			String transactiontype, Double transactionAmount) {
		super();
		
		this.transactiondescription = transactiondescription;
		
		this.transactiontype = transactiontype;
		this.transactionAmount = transactionAmount;
		
	}
	
	
	
	
	


	

}
