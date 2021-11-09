package com.scsb.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scsb.model.Sheet;
import com.scsb.model.SheetApproval;



public interface RecordSheetRepository extends JpaRepository<SheetApproval, String>
{
	@Query(value = "SELECT * FROM SCSB_SHEET ", nativeQuery = true)
	List<Sheet> getAllSheets();
	
	@Query(value = "SELECT * FROM SCSB_SHEET LEFT JOIN SCSB_SHEET_APPROVAL ON SCSB_SHEET.ID = SCSB_SHEET_ID WHERE SCSB_SHEET.APPLICANT_ID = ?1", nativeQuery = true)
	List<Sheet> getSheetByApplicant(String memberId);

	@Query(value = "SELECT * FROM SCSB_SHEET WHERE ID = ?1", nativeQuery = true)
	Sheet getSheetById(int id);

	@Query(value = "SELECT * FROM SCSB_SHEET_APPROVAL WHERE SCSB_SHEET_ID = ?1", nativeQuery = true)
	List<SheetApproval> getApplicantById(int id);

}
