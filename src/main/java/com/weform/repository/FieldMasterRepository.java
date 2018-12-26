package com.weform.repository;

import com.weform.entity.FieldMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Kason
 * @Date: 2018/12/12 18:57
 */
public interface FieldMasterRepository extends JpaRepository<FieldMaster, String> {

    Long countByFormIdAndDetailStatusNot(String formId, Integer detailStatusNot);

    Page<FieldMaster> findByFormIdAndDetailStatusNot(String formId, Integer status, Pageable pageable);
}
