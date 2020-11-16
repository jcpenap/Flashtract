package com.flashtract.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private int id;
	private String name;
	private String lastname;
	private String username;
	private String password;
	private ProfileDto profile;
}
