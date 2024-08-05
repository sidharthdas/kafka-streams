package com.kafkastream.controller;

import com.kafkastream.model.LoanDetail;
import com.kafkastream.service.LoanService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan/")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("add")
    public void createLoan(@RequestBody LoanDetail loanDetail) {
        loanService.pushToKafkaTopic(loanDetail);
    }
}
