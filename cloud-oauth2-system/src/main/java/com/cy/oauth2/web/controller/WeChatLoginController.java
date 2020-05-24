package com.cy.oauth2.web.controller;


import com.cy.base.result.ResultData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@RestController
@RequestMapping("/system/weChat")
public class WeChatLoginController {

    public static final String APPID="wxbcbf267fee301ca0";
    public static final String AppSecret = "02299ed1d1fa59a0456df9c7805b6377";

    @RequestMapping("/QRcode")
    public Object publicStringindex() {

        try {
            String oauthUrl ="https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

            String redirect_uri = URLEncoder.encode("http://www.cy925.top","utf-8");

            oauthUrl =  oauthUrl.replace("APPID",APPID).replace("REDIRECT_URI",redirect_uri).replace("SCOPE","snsapi_login");

            return ResultData.ok(oauthUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.build(400,"转换错误");
        }
    }





}
