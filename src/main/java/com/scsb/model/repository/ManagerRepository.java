package com.scsb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scsb.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, String>
{
	Manager findByZhhoAndMimaAndStatus(String zhho, String mima, String status);

}
