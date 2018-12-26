package com.weform.repository;

import com.weform.entity.FormMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Kason
 * @Date: 2018/12/11 16:00
 */
public interface FormMasterRepository extends JpaRepository<FormMaster,String> {

   Page<FormMaster> findByFormStatusNotAndOpenid(Integer formStatus,String openid, Pageable pageable);

   Page<FormMaster> findByFormStatusAndOpenid(Integer formStatus,String openid, Pageable pageable);

   FormMaster findByFormIdAndFormStatusNot(String formId,Integer formStatus);

}
