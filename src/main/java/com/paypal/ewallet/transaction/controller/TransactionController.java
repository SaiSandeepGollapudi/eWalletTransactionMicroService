package com.paypal.ewallet.transaction.controller;

import com.paypal.ewallet.transaction.service.TransactionService;
import com.paypal.ewallet.transaction.service.resource.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction/{user-id}")//transaction service calls the wallet service
    public ResponseEntity<Boolean> transaction(@PathVariable("user-id") Long userId, @RequestBody TransactionRequest transactionRequest) {
      //  transactionService.performTransaction(userId, transactionRequest);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}