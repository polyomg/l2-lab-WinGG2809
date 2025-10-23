package com.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.entity.Account;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String uri = request.getRequestURI();

        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            session.setAttribute("securityUri", uri);
            session.setAttribute("message", "Vui lòng đăng nhập để truy cập!");

            response.sendRedirect("/auth/login");
            return false;
        }

        if (uri.startsWith("/admin") && !user.isAdmin()) {
            session.setAttribute("message", "Bạn không có quyền truy cập trang quản trị!");
            response.sendRedirect("/auth/login");
            return false;
        }
        return true;
    }
}
