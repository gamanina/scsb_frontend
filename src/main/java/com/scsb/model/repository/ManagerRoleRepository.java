package com.scsb.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scsb.model.ManagerRole;

public interface ManagerRoleRepository extends JpaRepository<ManagerRole, String>
{
	Optional<ManagerRole> findById(String id);
}
