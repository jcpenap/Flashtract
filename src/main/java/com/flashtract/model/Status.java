package com.flashtract.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STATUS")
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Contract> contractList = new ArrayList<>();
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Invoice> invoiceList = new ArrayList<>();
	
}
