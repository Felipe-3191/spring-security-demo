package com.eazybytes.controller;

import com.eazybytes.model.Loans;
import com.eazybytes.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private   LoanRepository repository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam int id){
        List<Loans> loans = repository.findByCustomerIdOrderByStartDtDesc(id);
        if (loans != null) {
            return loans;
        }

        return null;

    }
}
