package com.flashtract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flashtract.dto.LoginDto;
import com.flashtract.dto.UserDto;
import com.flashtract.exception.CustomException;
import com.flashtract.model.Profile;
import com.flashtract.model.User;
import com.flashtract.repository.ProfileRepository;
import com.flashtract.repository.UserRepository;
import com.flashtract.security.JWTToken;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private JWTToken jwtToken;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserDto> findAll() {
		return userRepository.findAll().stream().map(entity -> {
			UserDto userDto = modelMapper.map(entity, UserDto.class);
			userDto.setPassword(null);
			return userDto;
		}).collect(Collectors.toList());
	}

	@Override
	public LoginDto autenticate(String username, String password) throws CustomException {
		try {
			return userRepository.findByUsernameAndPassword(username, password).map(entity -> {
				String token = jwtToken.generate(username);
				LoginDto login = new LoginDto();
				login.setUsername(entity.getUsername());
				login.setToken(token);
				return login;
			}).orElseThrow(CustomException::new);
		} catch (CustomException e) {
			throw new CustomException("Invalid Password", String.valueOf(HttpStatus.PRECONDITION_FAILED.value()));
		}
	}
	
	@Override
	public UserDto save(UserDto dto) throws CustomException {
		if(!userRepository.findByUsername(dto.getUsername()).isPresent()) {
			Profile profile = findProfileById(dto.getProfile().getId());
			User user = modelMapper.map(dto, User.class);
			user.setProfile(profile);
			UserDto userDto = modelMapper.map(userRepository.save(user), UserDto.class);
			userDto.setPassword(null);
			return userDto;
		} else {
			throw new CustomException("Username already exist", String.valueOf(HttpStatus.PRECONDITION_FAILED.value()));
		}
	}
	
	/**
	 * find profile by id
	 * @param dto
	 * @return
	 * @throws CustomException
	 */
	private Profile findProfileById(int id) throws CustomException {
		return profileRepository.findById(id).map(profile -> profile)
				.orElseThrow(() -> new CustomException("Profile not found", String.valueOf(HttpStatus.PRECONDITION_FAILED.value())));
	}

}
