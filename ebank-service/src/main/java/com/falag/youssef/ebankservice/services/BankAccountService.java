package com.falag.youssef.ebankservice.services;

import lombok.AllArgsConstructor;
import com.falag.youssef.ebankservice.entities.BankAccount;
import com.falag.youssef.ebankservice.feign.CustomerRestClient;
import com.falag.youssef.ebankservice.model.Customer;
import com.falag.youssef.ebankservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BankAccountService {

    private final BankAccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;

    public List<BankAccount> getAllBankAccounts() {
        return accountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id) {
        BankAccount bankAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        bankAccount.setCustomer(customerRestClient.getCustomer(bankAccount.getCustomerId()));
        return bankAccount;
    }

    public BankAccount save(BankAccount bankAccount) {
        try {
            Customer customer = customerRestClient.getCustomer(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return accountRepository.save(bankAccount);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
