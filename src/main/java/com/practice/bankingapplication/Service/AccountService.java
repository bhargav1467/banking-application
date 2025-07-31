package com.practice.bankingapplication.Service;

import com.practice.bankingapplication.Dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createNew(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit (Long id, Double amount);
    AccountDto withdraw (Long id, Double amount);
    List<AccountDto>getAllAccount();

    void deleteAccount(Long id);
}
