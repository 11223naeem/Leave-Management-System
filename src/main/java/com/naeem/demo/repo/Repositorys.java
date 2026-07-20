package com.naeem.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naeem.demo.model.Account;

@Repository
public interface Repositorys extends JpaRepository<Account, String>  {

	

	Account findByname(String name);

	Account findBypass(String pass);

	

	

	

	

}