package com.practice.bankingapplication.Controller;

import com.practice.bankingapplication.Dto.AccountDto;
import com.practice.bankingapplication.Dto.TransactionRequestDTO;
import com.practice.bankingapplication.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/banking")
public class AccountController {
private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add Account REST API
    @PostMapping("/create")
   public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createNew(accountDto), HttpStatus.CREATED);
   }
   // Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }
   // Deposit REST API
   /* @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        AccountDto accountDto = accountService.deposit(id, request.get("amount")); // we can introduce local variable also for request.get
        return ResponseEntity.ok(accountDto);
    } */

    @PutMapping("/deposit/{id}")
    public ResponseEntity<?> deposit(@PathVariable Long id, @RequestBody TransactionRequestDTO request) {
        try {
            Double amount = request.getAmount();
            if (amount == null || amount <= 0) {
                return ResponseEntity.badRequest().body("Invalid deposit amount");
            }
            AccountDto updatedAccount = accountService.deposit(id, amount);
            return ResponseEntity.ok("Deposited Successfully. Current Balance: " + updatedAccount.getBalance());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    // Withdraw REST API
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<?> withdraw(@PathVariable Long id, @RequestBody TransactionRequestDTO request) {
        try {
            Double amount = request.getAmount();
            if (amount == null || amount <= 0) {
                return ResponseEntity.badRequest().body("Invalid withdraw amount");
            }
            AccountDto updatedAccount = accountService.withdraw(id, amount);
            return ResponseEntity.ok("Withdrawn Successfully. Current Balance: " + updatedAccount.getBalance());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get All Accounts
    @GetMapping("/get/all")
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        List<AccountDto> accountDtos = accountService.getAllAccount();
        return ResponseEntity.ok(accountDtos);
    }
    // Delete Account REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted Successfully");

    }

}
