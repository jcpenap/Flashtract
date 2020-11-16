package com.flashtract.service;

import java.util.List;

import com.flashtract.dto.ProfileDto;
import com.flashtract.exception.CustomException;

public interface ProfileService {
	public List<ProfileDto> findAll() throws CustomException;
	public ProfileDto save(ProfileDto Dto) throws CustomException;
}
