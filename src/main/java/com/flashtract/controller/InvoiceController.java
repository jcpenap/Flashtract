package com.flashtract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flashtract.dto.InvoiceDto;
import com.flashtract.exception.CustomException;
import com.flashtract.service.InvoiceService;
import com.flashtract.util.Response;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Response<Object>> findAll() throws CustomException {
		try {
			return ResponseEntity.ok(new Response<>(invoiceService.findAll(), "Contracts Found"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response<>(null, e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Response<Object>> save(@RequestBody InvoiceDto dto) throws CustomException {
		try {
			return ResponseEntity.ok(new Response<>(invoiceService.save(dto), "Invoice Found"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response<>(null, e.getMessage()));
		}
	}
	
}
