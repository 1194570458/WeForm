package com.weform.controller;

import com.weform.constant.CookieConstant;
import com.weform.entity.FormMaster;
import com.weform.enums.ResultEnum;
import com.weform.exception.FormException;
import com.weform.service.FormMasterService;
import com.weform.utils.CookieUtil;
import com.weform.utils.ResultVOUtil;
import com.weform.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author: Kason
 * @Date: 2018/12/11 18:01
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/form")
public class FormController {

    @Autowired
    private FormMasterService formMasterService;

    /**
     * 创建表单
     *
     * @param formMaster
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<FormMaster> create(@Valid @RequestBody FormMaster formMaster, BindingResult bindingResult) {
        checkFormMaster(bindingResult, formMaster);
        FormMaster reuslt = formMasterService.save(formMaster);
        return ResultVOUtil.success(reuslt);
    }

    /**
     * 获取表单
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO<FormMaster> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "10") Integer size,
                                     HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        Page<FormMaster> result = formMasterService.findListNotEqualsStatus(cookie.getValue(), page, size);
        return ResultVOUtil.success(result.getContent());
    }

    @PostMapping("/update")
    public ResultVO<FormMaster> update(@Valid @RequestBody FormMaster formMaster, BindingResult bindingResult) {
        checkFormMaster(bindingResult, formMaster);
        FormMaster result = formMasterService.save(formMaster);
        return ResultVOUtil.success(result);
    }

    /**
     * 检查必填字段
     *
     * @param bindingResult
     * @param formMaster
     */
    private void checkFormMaster(BindingResult bindingResult, FormMaster formMaster) {
        if (bindingResult.hasErrors()) {
            log.error("【创建表单】参数不正确 formMaster={}", formMaster);
            throw new FormException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
    }

    /**
     * 删除表单
     *
     * @param formid
     * @return
     */
    @GetMapping("/del")
    public ResultVO del(@RequestParam("formId") String formid,
                        HttpServletRequest request) {
        FormMaster formMaster = new FormMaster();
        formMaster.setFormId(formid);
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        formMaster.setOpenid(cookie.getValue());
        formMasterService.delForm(formMaster);
        return ResultVOUtil.success();
    }

}
