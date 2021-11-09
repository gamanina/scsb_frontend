package com.scsb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scsb.model.IndexBannerSetting;
import com.scsb.model.RemindEmailSetting;

public interface IndexBannerSettingRepository extends JpaRepository<IndexBannerSetting, String>
{
	@Query(value = "SELECT * FROM SCSB_INDEX_BANNER_SETTING WHERE ROWNUM = 1 ORDER BY MODIFY_TIME ", nativeQuery = true)
	IndexBannerSetting get();
}
