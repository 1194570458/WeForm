package com.weform.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weform.config.ProjectRestTemplateConfig;
import com.weform.config.ProjectUrlConfig;
import com.weform.config.WechatAccountConfig;
import com.weform.constant.CookieConstant;
import com.weform.service.WebSocket;
import com.weform.utils.CookieUtil;
import com.weform.utils.ImageUtil;
import com.weform.utils.KeyUtil;
import com.weform.utils.ResultVOUtil;
import com.weform.vo.ResultVO;
import com.weform.vo.WechatUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;

/**
 * @Author: Kason
 * @Date: 2018/12/24 9:21
 */

@RestController
@RequestMapping("/wechat")
@Slf4j
@CrossOrigin
public class WechatController {


    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Autowired
    private ProjectRestTemplateConfig restTemplateConfig;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @Autowired
    private WebSocket webSocket;

    private final static String url =
            "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @GetMapping("/unionid")
    public ResultVO openid(@RequestParam("code") String code,
                           @RequestParam("nickName") String nickName,
                           @RequestParam("avatarUrl") String avatarUrl,
                           @RequestParam("state") String state) {
        String result = restTemplateConfig.restTemplate().getForObject(
                String.format(url,
                        wechatAccountConfig.getAppId(),
                        wechatAccountConfig.getSecret(),
                        code), String.class

        );
        /**
         * {"session_key":"LrE1LqWu\/lkuzxi\/dDJnBQ==","openid":"o5xQN5D1ee9sej8Twt51HVQhkImU"}
         */
        log.info("【wechat获取unionid】 result={}", result);
        WechatUserInfo userInfo = new Gson().fromJson(result, WechatUserInfo.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("url", projectUrlConfig.getForm() + "/wechat/login?openid=" + userInfo.getOpenid());
        map.put("state", state);
        map.put("nickName", nickName);
        map.put("avatarUrl", avatarUrl);
        map.put("openid", userInfo.getOpenid());
        webSocket.sendMessage(new Gson().toJson(ResultVOUtil.success(map)));
        return ResultVOUtil.success(map);
    }

    @GetMapping("/login")
    public ResultVO login(@RequestParam("openid") String openid, HttpServletResponse response) {
        CookieUtil.set(response,CookieConstant.TOKEN,openid,CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }

    private static final String ACCESS_TOKEN_URL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final String QR_CODE_URL =
            "https://api.weixin.qq.com/wxa/getwxacode?access_token=%s";

    @GetMapping("/qrcode")
    public ResultVO QRcode() throws IOException {
        String result = restTemplateConfig.restTemplate().getForObject(String.format(
                ACCESS_TOKEN_URL,
                wechatAccountConfig.getAppId(),
                wechatAccountConfig.getSecret()
        ), String.class);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

        String state = KeyUtil.createNumber();
        String qrcode = ImageUtil.getBase64(String.format(QR_CODE_URL, jsonObject.get("access_token").getAsString()), state);
        HashMap<String, String> map = new HashMap<>();
        map.put("state", state);
        map.put("img", qrcode);
        return ResultVOUtil.success(map);
    }

    @GetMapping("/logout")
    public ResultVO logout(HttpSession session) {
        session.removeAttribute(CookieConstant.TOKEN);
        return ResultVOUtil.success();
    }

}
