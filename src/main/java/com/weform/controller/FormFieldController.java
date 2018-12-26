package com.weform.controller;

import com.weform.constant.CookieConstant;
import com.weform.entity.FormField;
import com.weform.service.FormFieldService;
import com.weform.utils.CookieUtil;
import com.weform.utils.ResultVOUtil;
import com.weform.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/12 15:25
 */

@RestController
@RequestMapping("/field")
@Slf4j
@CrossOrigin
public class FormFieldController {

    @Autowired
    private FormFieldService formFieldService;

    //添加/修改 字段
    @PostMapping("/save")
    public ResultVO<FormField> save(@Valid @RequestBody FormField formField,
                                    HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        FormField field = formFieldService.save(formField,cookie.getValue());
        return ResultVOUtil.success(field);
    }


    //根绝表单查找所有字段
    @GetMapping("/list")
    public ResultVO<List<FormField>> list(String formId) {
        List<FormField> formFieldList = formFieldService.list(formId);
        return ResultVOUtil.success(formFieldList);
    }

    //删除字段
    @PostMapping("/del")
    public ResultVO delField(@RequestParam("fieldId") String fieldId,
                             HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        formFieldService.delField(fieldId,cookie.getValue());
        return ResultVOUtil.success();
    }


}
