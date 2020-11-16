package com.flashtract.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashtract.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
