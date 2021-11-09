package com.scsb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scsb.model.RemindEmailSetting;

public interface RemindEmailSettingRepository extends JpaRepository<RemindEmailSetting, String>
{

	//拿取設定
	@Query(value = "SELECT * FROM SCSB_REMIND_EMAIL_SETTING WHERE ROWNUM = 1 ORDER BY MODIFY_TIME ", nativeQuery = true)
	RemindEmailSetting get();

}
