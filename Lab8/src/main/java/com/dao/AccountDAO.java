package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
}
