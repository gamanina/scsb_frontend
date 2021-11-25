package com.src.scsb.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.src.scsb.config.Constants;
import com.src.scsb.model.Sheet;
import com.src.scsb.model.repository.SheetRepository;

@Service
public class SheetService 
{
	@Autowired
	private SheetRepository repository;

	// For首頁輪播
	public List<Sheet> getIndexBanners() 
	{
		LocalDateTime now = LocalDateTime.now();
		return repository.getIndexBanners(now, Constants.INDEX_BANNER_SHEET_TYPE, Constants.SHEET_STATUS_PROCESSING,
				Constants.SHEET_STATUS_PASSED);
	}
	
	public List<Sheet> getPassedListByType(LocalDateTime now, String type)
	{
		return repository.getListByTypeAndStatus(now, type, Constants.SHEET_STATUS_PASSED);
	}
	
	public List<Sheet> getListByNewsLimit3(LocalDateTime now)
	{
		return repository.getListByNewsLimit3(now);
	}
	
	public List<Sheet> getDetail(LocalDateTime now,Number id)
	{
		return repository.getDetail(now,id);
	}

	public List<Sheet> getDetailList(LocalDateTime now, String type) {
		return repository.getDetailList(now,type);
	}
	
	public Page<Sheet> getDetailPageByTypeAndCategory(Timestamp now, String type, String category, PageRequest pageable) {
		return repository.findAll(new Specification<Sheet>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Sheet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            List<Predicate> list = new ArrayList<Predicate>();
	            
	            list.add(criteriaBuilder.lessThan(root.get("onTime"), now));
	            list.add(criteriaBuilder.greaterThan(root.get("offTime"), now));
	            list.add(criteriaBuilder.equal(root.get("type"), type));
	            if (!StringUtils.isBlank(category)) {
	            	list.add(criteriaBuilder.equal(root.get("category"), category));
	            }
	            list.add(criteriaBuilder.equal(root.get("status"), 1));
	            
	            // 排序
//	            query.orderBy(criteriaBuilder.desc(root.get("modifyTime")));

	            Predicate[] p = new Predicate[list.size()];
	            return criteriaBuilder.and(list.toArray(p));
			}
		}, pageable.withSort(Sort.by("modifyTime").descending()));
	}
	
	public List<Sheet> getDetailListByTypeAndCategory(Timestamp now, String type, String category) {
		return repository.findAll(new Specification<Sheet>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<Sheet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				list.add(criteriaBuilder.lessThan(root.get("onTime"), now));
				list.add(criteriaBuilder.greaterThan(root.get("offTime"), now));
				list.add(criteriaBuilder.equal(root.get("type"), type));
				if (!StringUtils.isBlank(category)) {
					list.add(criteriaBuilder.equal(root.get("category"), category));
				}
				list.add(criteriaBuilder.equal(root.get("status"), 1));
				
				// 排序
	            query.orderBy(criteriaBuilder.desc(root.get("modifyTime")));
				
				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		});
	}
	
}
