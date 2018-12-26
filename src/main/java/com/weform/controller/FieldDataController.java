package com.weform.controller;

import com.weform.constant.CookieConstant;
import com.weform.enums.ResultEnum;
import com.weform.exception.FormException;
import com.weform.service.FieldDataService;
import com.weform.utils.CookieUtil;
import com.weform.utils.ResultVOUtil;
import com.weform.vo.FieldDataVO;
import com.weform.vo.FieldMasterVO;
import com.weform.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: Kason
 * @Date: 2018/12/13 9:55
 */
@RestController
@CrossOrigin
@RequestMapping("/data")
@Slf4j
public class FieldDataController {

    @Autowired
    private FieldDataService dataService;

    /**
     * 添加数据
     *
     * @param fieldMasterVO
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<FieldMasterVO> create(@Valid @RequestBody FieldMasterVO fieldMasterVO,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("【添加数据】 参数错误 fieldMasterVO={}", fieldMasterVO);
            throw new FormException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
        FieldMasterVO masterVO = dataService.create(fieldMasterVO);
        return ResultVOUtil.success(masterVO);
    }

    @GetMapping("/list")
    public ResultVO<FieldMasterVO> list(@RequestParam("formId") String formId,
                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                        HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        List<List<FieldDataVO>> list = dataService.list(formId, page, size, cookie.getValue());
        return ResultVOUtil.success(list);
    }


}
