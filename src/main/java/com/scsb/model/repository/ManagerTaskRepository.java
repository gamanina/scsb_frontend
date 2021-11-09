package com.scsb.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scsb.model.Manager;
import com.scsb.model.ManagerTask;

public interface ManagerTaskRepository extends JpaRepository<ManagerTask, String>
{
	@Query(value = "SELECT * FROM SCSB_MANAGER_TASK WHERE ID IN (?1)", nativeQuery = true)
	List<ManagerTask> findByIds(List<String> num);
}
