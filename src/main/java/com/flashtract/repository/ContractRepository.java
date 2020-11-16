package com.flashtract.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashtract.exception.CustomException;
import com.flashtract.model.Contract;
import com.flashtract.model.User;

public interface ContractRepository extends JpaRepository<Contract, Integer>{
	public List<Contract> findByVendor(User vendor) throws CustomException;
}
