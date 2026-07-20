package com.naeem.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naeem.demo.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findByUsername(String username);
}