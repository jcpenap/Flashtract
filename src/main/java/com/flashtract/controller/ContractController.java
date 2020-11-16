package com.flashtract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flashtract.dto.ContractDto;
import com.flashtract.exception.CustomException;
import com.flashtract.service.ContractService;
import com.flashtract.util.Response;

@RestController
@RequestMapping("/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Response<Object>> findAll() throws CustomException {
		try {
			return ResponseEntity.ok(new Response<>(contractService.findAll(), "Contracts Found"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response<>(null, e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Response<Object>> save(@RequestBody ContractDto dto) throws CustomException {
		try {
			return ResponseEntity.ok(new Response<>(contractService.save(dto), "Contract Created"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response<>(null, e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/vendor/{vendorId}", method = RequestMethod.GET)
	public ResponseEntity<Response<Object>> findByVendorId(@PathVariable("vendorId") int vendorId) throws CustomException {
		try {
			return ResponseEntity.ok(new Response<>(contractService.findByVendor(vendorId), "Contracts Found"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response<>(null, e.getMessage()));
		}
	}
}
