package com.controller;

import com.service.CookieService;
import com.service.ParamService;
import com.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2() {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        if (username.equals("poly") && password.equals("123")) {
            sessionService.set("username", username);


            if (remember) {
                cookieService.add("user", username, 10 * 24);
            } else {
                cookieService.remove("user");
            }

            sessionService.remove("error");
            sessionService.set("nice", "Đăng nhập thành công!");
        } else {
            sessionService.remove("nice");
            sessionService.set("error", "Sai tên đăng nhập hoặc mật khẩu!");
        }

        return "/account/login";
    }
}
