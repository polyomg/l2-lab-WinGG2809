package com.service;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SessionService {
    @Autowired
    HttpSession session;

    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        try {
            return (T) session.getAttribute(name);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T get(String name, T defaultValue) {
        T value = get(name);
        return (value != null) ? value : defaultValue;
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }
}
