package com.flashtract.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String lastname;
	private String username;
	private String password;
	@ManyToOne
    @JoinColumn(name ="id_profile", nullable = false)
    private Profile profile;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Contract> clientsList = new ArrayList<>();
	
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<Contract> vendorsList = new ArrayList<>();
}
