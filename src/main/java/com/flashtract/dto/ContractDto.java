package com.flashtract.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractDto {
	
	private Integer id;

	private String description;

	private Date creationDate;

	private UserDto client;

	private UserDto vendor;

	private Double amount;

	private StatusDto status;
}
