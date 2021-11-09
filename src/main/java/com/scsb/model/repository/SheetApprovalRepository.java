package com.scsb.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scsb.model.Sheet;
import com.scsb.model.SheetApproval;



public interface SheetApprovalRepository extends JpaRepository<SheetApproval, String>
{
	
	@Query(value = "SELECT * FROM SCSB_SHEET_APPROVAL WHERE SCSB_SHEET_ID = ?1 ORDER BY CREATE_TIME ASC", nativeQuery = true)
	List<SheetApproval> getApplicantById(int id);
	
	@Query(value = "SELECT * FROM SCSB_SHEET_APPROVAL WHERE SCSB_SHEET_ID = ?1 AND SORT = '0'", nativeQuery = true)
	SheetApproval getgetAapplicant(int id);
	
	@Query(value = "SELECT * FROM SCSB_SHEET_APPROVAL WHERE SCSB_SHEET_ID = ?1 AND SORT = ?2", nativeQuery = true)
	SheetApproval getApprovalBySheetIdAndStep(int id, int sort);

	
}
