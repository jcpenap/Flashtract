package com.flashtract.service;

import java.util.List;

import com.flashtract.dto.ContractDto;
import com.flashtract.exception.CustomException;

public interface ContractService {
	public List<ContractDto> findAll() throws CustomException;
	public ContractDto save(ContractDto dto) throws Exception;
	public List<ContractDto> findByVendor(int vendorId) throws Exception;
}
