package com.practice.bankingapplication.Mapper;

import com.practice.bankingapplication.Dto.AccountDto;
import com.practice.bankingapplication.Entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                accountDto.getPhoneNo()
        );
        return account;
    }

    public static AccountDto mapToAccountDto( Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getPhoneNo()
        );
        return accountDto;
    }
}
