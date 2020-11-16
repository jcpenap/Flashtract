package com.flashtract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashtract.dto.ProfileDto;
import com.flashtract.exception.CustomException;
import com.flashtract.model.Profile;
import com.flashtract.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProfileDto> findAll() throws CustomException {
		return profileRepository.findAll().stream().map(profile -> {
			ProfileDto profileDto = modelMapper.map(profile, ProfileDto.class);
			return profileDto;
		}).collect(Collectors.toList());
	}

	@Override
	public ProfileDto save(ProfileDto dto) throws CustomException {
		Profile profile = profileRepository.save(modelMapper.map(dto, Profile.class));
		return modelMapper.map(profile, ProfileDto.class);
	}

}
