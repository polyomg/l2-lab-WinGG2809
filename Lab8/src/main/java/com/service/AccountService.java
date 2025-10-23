package com.service;

import com.entity.Account;

public interface AccountService {
    Account findById(String username);
}
