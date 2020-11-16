package com.flashtract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flashtract.dto.UserDto;
import com.flashtract.exception.CustomException;
import com.flashtract.service.UserService;
import com.flashtract.util.Response;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Response<Object>> findAll() throws CustomException {
		try {
			return ResponseEntity.ok(new Response<>(userService.findAll(), "Users Found"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response<>(null, e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Response<Object>> save(@RequestBody UserDto dto) throws CustomException {
		try {
			return ResponseEntity.ok(new Response<>(userService.save(dto), "User Created"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response<>(null, e.getMessage()));
		}
	}
	
}
