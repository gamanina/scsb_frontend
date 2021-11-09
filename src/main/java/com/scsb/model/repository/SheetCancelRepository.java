package com.scsb.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scsb.model.Sheet;
import com.scsb.model.SheetApproval;
import com.scsb.model.SheetCancel;



public interface SheetCancelRepository extends JpaRepository<SheetCancel, String>
{
	@Query(value = "SELECT * FROM SCSB_SHEET_CANCEL WHERE SCSB_SHEET_ID = ?1 ORDER BY CREATE_TIME ASC", nativeQuery = true)
	List<SheetCancel> getCancelApplicantById(int id);
	
	@Query(value = "SELECT * FROM SCSB_SHEET_CANCEL WHERE SCSB_SHEET_ID = ?1", nativeQuery = true)
	List<SheetCancel> getCancelListBySheetId(int id);
	
	@Query(value = "SELECT * FROM (SELECT * FROM (SELECT * FROM SCSB_SHEET_CANCEL WHERE SORT = 0) ORDER BY LOOP DESC) WHERE SCSB_SHEET_ID = ?1 AND ROWNUM = 1", nativeQuery = true)
	SheetCancel getCancelApplicantBySheetId(int id);
}
