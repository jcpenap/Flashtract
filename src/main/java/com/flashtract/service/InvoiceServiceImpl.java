package com.flashtract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flashtract.dto.InvoiceDto;
import com.flashtract.exception.CustomException;
import com.flashtract.model.Contract;
import com.flashtract.model.Invoice;
import com.flashtract.model.Status;
import com.flashtract.repository.ContractRepository;
import com.flashtract.repository.InvoiceRepository;
import com.flashtract.repository.StatusRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<InvoiceDto> findAll() throws CustomException {
		return invoiceRepository
				.findAll()
				.stream()
				.map(entity -> {
					InvoiceDto invoiceDto = modelMapper.map(entity, InvoiceDto.class);
					invoiceDto.getContract().getClient().setPassword(null);
					invoiceDto.getContract().getVendor().setPassword(null);
					return invoiceDto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public InvoiceDto save(InvoiceDto dto) throws CustomException {
		Contract contract = findContractById(dto.getContract().getId());
		Double totalInvoiced = dto.getAmount();
		
		for(Invoice invoice: contract.getInvoiceList()) {
			totalInvoiced += invoice.getAmount();
		}
		
		Double amountContract = contract.getAmount();
		
		if(amountContract>= totalInvoiced) {
			Invoice invoice = modelMapper.map(dto, Invoice.class);
			invoice.setStatus(findStatus(3));
			invoice.setContract(contract);
			Invoice response = invoiceRepository.save(invoice);
			InvoiceDto invoiceDto = modelMapper.map(response, InvoiceDto.class);
			invoiceDto.getContract().getClient().setPassword(null);
			invoiceDto.getContract().getVendor().setPassword(null);
			return invoiceDto;
		}
		throw new CustomException("Invoice total amount is greater than contract amount", String.valueOf(HttpStatus.PRECONDITION_FAILED.value())); 
		
	}

	private Contract findContractById(int id) throws CustomException {
		return contractRepository
				.findById(id)
				.map(contract -> contract)
				.orElseThrow(() -> new CustomException("Contract not found", String.valueOf(HttpStatus.PRECONDITION_FAILED.value())));
	}
	
	private Status findStatus(int id) throws CustomException {
		return statusRepository.findById(id).map(status -> status)
				.orElseThrow(()-> new CustomException("Status not found", String.valueOf(HttpStatus.PRECONDITION_FAILED.value())));
	}


}
