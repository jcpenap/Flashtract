package com.flashtract.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONTRACT")
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@ManyToOne
    @JoinColumn(name ="ID_CLIENT", nullable = false)
    private User client;
	
	@ManyToOne
    @JoinColumn(name ="ID_VENDOR", nullable = false)
    private User vendor;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@ManyToOne
    @JoinColumn(name ="id_status")
    private Status status;
	
	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<Invoice> invoiceList = new ArrayList<>();
}
