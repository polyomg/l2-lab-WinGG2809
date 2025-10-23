package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.AccountDAO;
import com.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO dao;

    @Override
    public Account findById(String username) {
        return dao.findById(username).orElse(null);
    }
}
