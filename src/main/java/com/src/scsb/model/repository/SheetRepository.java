package com.src.scsb.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.src.scsb.model.Sheet;

public interface SheetRepository extends JpaRepository<Sheet, String>
{
	@Query(value = "SELECT * FROM SCSB_SHEET WHERE ON_TIME < ?1 AND OFF_TIME > ?1 AND TYPE = ?2 AND (STATUS = ?3 OR STATUS = ?4)", nativeQuery = true)
	List<Sheet> getIndexBanners(LocalDateTime time, String type, String processing, String passed);
	
	@Query(value = "SELECT * FROM SCSB_SHEET WHERE ON_TIME < ?1 AND OFF_TIME > ?1 AND TYPE = ?2 AND STATUS = ?3 ORDER BY MODIFY_TIME DESC", nativeQuery = true)
	List<Sheet> getListByTypeAndStatus(LocalDateTime time, String type, String passed);
	
	@Query(value = "SELECT * FROM SCSB_SHEET WHERE ON_TIME < ?1 AND OFF_TIME > ?1 AND TYPE = 1 AND STATUS = 1 AND ROWNUM <= 3 ORDER BY MODIFY_TIME DESC", nativeQuery = true)
	List<Sheet> getListByNewsLimit3(LocalDateTime time);

	@Query(value = "SELECT * FROM SCSB_SHEET WHERE ON_TIME < ?1 AND OFF_TIME > ?1  AND ID = ?2 ", nativeQuery = true)
	List<Sheet> getDetail(LocalDateTime now, Number id);

	@Query(value = "SELECT * FROM SCSB_SHEET WHERE ON_TIME < ?1 AND OFF_TIME > ?1  AND TYPE = ?2 AND STATUS = 1 AND ROWNUM <= 3 ORDER BY MODIFY_TIME DESC", nativeQuery = true)
	List<Sheet> getDetailList(LocalDateTime now, String type);
}
