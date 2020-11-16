package com.flashtract.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvoiceDto {
	private Integer id;
	private String description;
	private Double amount;
    private ContractDto contract;
	private Date creationDate;
    private StatusDto status;
}
