package com.weform.aspect;

import com.weform.constant.CookieConstant;
import com.weform.exception.UserAuthorizeException;
import com.weform.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: Kason
 * @Date: 2018/12/25 10:20
 */
@Aspect
@Component
@Slf4j
public class FormAuthorizeAspect {

    @Pointcut("execution(public * com.weform.controller..*(..))" +
            "&& !execution(public * com.weform.controller.Wechat*.*(..))" +
            "&& !execution(public * com.weform.controller.FieldDataController.create(..))" +
            "&& !execution(public * com.weform.controller.FormFieldController.list(..))")
    public void verify() {
    }

    @Before(value = "verify()")
    public void doVerify(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        log.info("url={}", request.getRequestURL());

        //mehtod
        log.info("method{}", request.getMethod());

        //ip
        log.info("ip={}", request.getRemoteAddr());

        //类方法
        log.info("class_method={}", joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName());

        //参数
        log.info("args={}", joinPoint.getArgs());

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登陆校验】Cookie中查不到token");
            throw new UserAuthorizeException();
        }
        if (StringUtils.isEmpty(cookie.getValue())) {
            log.warn("【登陆校验】Cookie中查不到token");
            throw new UserAuthorizeException();
        }
    }

}
