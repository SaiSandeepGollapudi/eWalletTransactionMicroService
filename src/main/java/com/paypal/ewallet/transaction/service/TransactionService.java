package com.paypal.ewallet.transaction.service;

import com.paypal.ewallet.transaction.service.resource.TransactionRequest;
import org.springframework.stereotype.Service;


public interface TransactionService {
    public boolean performTransaction(Long userId, TransactionRequest transactionRequest) ;

}
