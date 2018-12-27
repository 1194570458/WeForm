package com.weform.repository;

import com.weform.entity.FormField;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/12 9:02
 */
public interface FormFieldRepository extends JpaRepository<FormField, String> {

    List<FormField> findByFormIdAndFieldStatus(String formId, Integer status, Sort sort);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update FormField  set fieldStatus = -1 where formId = :formid")
    int deleteStatusByFormId(@Param("formid") String formid);
}
