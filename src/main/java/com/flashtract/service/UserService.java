package com.flashtract.service;

import java.util.List;

import com.flashtract.dto.LoginDto;
import com.flashtract.dto.UserDto;
import com.flashtract.exception.CustomException;

public interface UserService {
	/**
	 * find all users
	 * @return
	 * @throws CustomException
	 */
	public List<UserDto> findAll() throws CustomException;
	
	/**
	 * Authenticate the user with username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws CustomException
	 */
	public LoginDto autenticate(String username, String password) throws CustomException;
	
	/**
	 * Create a new user
	 * @param dto
	 * @return
	 * @throws CustomException
	 */
	public UserDto save(UserDto dto) throws CustomException;
}
