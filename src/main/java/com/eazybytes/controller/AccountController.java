package com.eazybytes.controller;

import com.eazybytes.model.Accounts;
import com.eazybytes.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountsRepository repository;

@GetMapping("/myAccount")
public Accounts getAccountDetails(@RequestParam int id){

    Accounts accounts = repository.findByCustomerId(id);
    if (accounts != null) {
        return accounts;
    }

    return null;

}





}
