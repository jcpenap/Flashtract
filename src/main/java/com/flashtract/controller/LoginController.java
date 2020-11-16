package com.flashtract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashtract.exception.CustomException;
import com.flashtract.service.UserService;
import com.flashtract.util.Response;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Response<Object>> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		try {
			return ResponseEntity.ok(new Response<>(userService.autenticate(username, password), "Authenticated"));
		} catch (CustomException e) {
			return ResponseEntity.status(Integer.valueOf(e.getErrorCode())).body(new Response<>(null, e.getMessage()));
		}
	}

}