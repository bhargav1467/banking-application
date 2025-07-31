package com.practice.bankingapplication.Service;

import com.practice.bankingapplication.Dto.AccountDto;
import com.practice.bankingapplication.Entity.Account;
import com.practice.bankingapplication.Mapper.AccountMapper;
import com.practice.bankingapplication.Repo.AccountRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public AccountDto createNew(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saved = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(saved) ;
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists!"));
        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists!"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account saved = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(saved);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {

        Account account = accountRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists!"));

        if( account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saved = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(saved);
    }

    @Override
    public List<AccountDto> getAllAccount() {
   List<Account> accountList = accountRepo.findAll();
     return accountList.stream().map(AccountMapper::mapToAccountDto)
             .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists!"));
        accountRepo.deleteById(id);
    }

}
