package com.weform.repository;

import com.weform.entity.FieldDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/12 18:59
 */
public interface FieldDetailRepository  extends JpaRepository<FieldDetail,String> {

    List<FieldDetail> findByMasterId(String masterId);
}
