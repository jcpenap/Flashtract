package com.flashtract.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flashtract.dto.ContractDto;
import com.flashtract.exception.CustomException;
import com.flashtract.model.Contract;
import com.flashtract.model.Status;
import com.flashtract.model.User;
import com.flashtract.repository.ContractRepository;
import com.flashtract.repository.StatusRepository;
import com.flashtract.repository.UserRepository;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	private ContractRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ContractDto> findAll() throws CustomException {
		return repository.findAll().stream().map(entity -> {
			Contract contract = repository.save(entity);
			ContractDto contractDto = modelMapper.map(contract, ContractDto.class);
			contractDto.getClient().setPassword(null);
			contractDto.getVendor().setPassword(null);
			return contractDto;
		}).collect(Collectors.toList());
	}

	@Override
	public ContractDto save(ContractDto dto) throws CustomException {
		Contract contract = modelMapper.map(dto, Contract.class);
		contract.setClient(findUserById(dto.getClient().getId()));
		contract.setCreationDate(new Date());
		contract.setStatus(findStatus(3));
		contract.setVendor(findUserById(dto.getVendor().getId()));
		ContractDto contractDto = modelMapper.map(repository.save(contract), ContractDto.class);
		contractDto.getClient().setPassword(null);
		contractDto.getVendor().setPassword(null);
		return contractDto;
	}

	private User findUserById(int id) throws CustomException {
		return userRepository.findById(id).map(user -> user)
				.orElseThrow(()-> new CustomException("User not found", String.valueOf(HttpStatus.PRECONDITION_FAILED.value())));
	}

	private Status findStatus(int id) throws CustomException {
		return statusRepository.findById(id).map(status -> status)
				.orElseThrow(()-> new CustomException("Status not found", String.valueOf(HttpStatus.PRECONDITION_FAILED.value())));
	}

	@Override
	public List<ContractDto> findByVendor(int vendorId) throws CustomException {
		User vendor = findUserById(vendorId);
		return repository.findByVendor(vendor).stream().map(entity -> {
			ContractDto contractDto = modelMapper.map(repository.save(entity), ContractDto.class);
			contractDto.getClient().setPassword(null);
			contractDto.getVendor().setPassword(null);
			return contractDto;
		}).collect(Collectors.toList());
	}
}
