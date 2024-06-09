package com.paypal.ewallet.transaction.service.impl;

import com.paypal.ewallet.transaction.service.TransactionService;
import com.paypal.ewallet.transaction.service.resource.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionServiceImpl  implements TransactionService {

    @Autowired
    RestTemplate template;

    public boolean performTransaction(Long userId, TransactionRequest transactionRequest) {
        try {//from here we need to call the wallet service so we need with transaction request

        }
    }

}