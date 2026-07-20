package com.naeem.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.naeem.demo.model.AdminAccount;

@Repository
public interface AdminRepository extends JpaRepository<AdminAccount, String> {

	AdminAccount findByname(String name);

}
