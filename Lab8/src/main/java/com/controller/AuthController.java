package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.entity.Account;
import com.service.AccountService;

@Controller
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        String message = (String) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        return "/auth/login";
    }

    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password) {
        Account user = accountService.findById(username);

        if (user == null) {
            model.addAttribute("message", "Invalid username!");
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
        } else {
            session.setAttribute("user", user);
            model.addAttribute("message", "Login successfully!");

            String securityUri = (String) session.getAttribute("securityUri");
            if (securityUri != null) {
                session.removeAttribute("securityUri");
                return "redirect:" + securityUri;
            }
            return "redirect:/home";
        }
        return "/auth/login";
    }

    @GetMapping("/auth/logout")
    public String logout() {
        session.removeAttribute("user");
        return "redirect:/auth/login";
    }

    @GetMapping({
            "/admin/dashboard",
            "/account/edit-profile",
            "/account/change-password",
            "/order/list"
    })
    public String TestPages(Model model, HttpServletRequest request) {
        String uri = request.getRequestURI();

        model.addAttribute("content", "Đã truy cập trang: " + uri);

        return "auth/test-page";
    }

    @GetMapping("/admin/home/index")
    public String adminHome(Model model) {
        model.addAttribute("content", "Đây là trang Admin Home (KHÔNG bị bảo vệ).");
        return "auth/test-page";
    }

    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home";
    }
}
