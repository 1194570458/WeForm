package com.weform.repository;

import com.weform.entity.FormField;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/12 9:02
 */
public interface FormFieldRepository extends JpaRepository<FormField, String> {

    List<FormField> findByFormIdAndFieldStatus(String formId, Integer status, Sort sort);

}
