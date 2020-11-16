package com.flashtract.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "INVOICE")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@ManyToOne
    @JoinColumn(name ="ID_CONTRACT", nullable = false)
    private Contract contract;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@ManyToOne
    @JoinColumn(name ="ID_STATUS")
    private Status status;
	
}
