package com.flashtract.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashtract.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
