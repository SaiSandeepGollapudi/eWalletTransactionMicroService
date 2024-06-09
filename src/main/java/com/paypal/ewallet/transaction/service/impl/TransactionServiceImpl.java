package com.paypal.ewallet.transaction.service.impl;

import com.paypal.ewallet.transaction.service.TransactionService;
import com.paypal.ewallet.transaction.service.resource.NotificationRequest;
import com.paypal.ewallet.transaction.service.resource.TransactionRequest;
import com.paypal.ewallet.transaction.service.resource.WalletTransactionRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionServiceImpl  implements TransactionService {

    @Autowired
    RestTemplate template;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public boolean performTransaction(Long userId, TransactionRequest transactionRequest) {
        try {//from here we need to call the wallet service, so we do it with transaction request
            WalletTransactionRequest walletTransactionRequest = new WalletTransactionRequest();// walletTransactionRequest
            // object is created to call the wallet as wallet will be performing transaction. user calls transaction it will
            // send below details to wallet like transfer money from wallet1 to wallet2 as

            walletTransactionRequest.setSenderId(userId);
            walletTransactionRequest.setReceiverId(transactionRequest.getReceiverId());
            walletTransactionRequest.setAmount(transactionRequest.getAmount());
            walletTransactionRequest.setDescription(transactionRequest.getDescription());
            walletTransactionRequest.setTransactionType(transactionRequest.getTransactionType());


            String url = "http://localhost:8082/wallet/transaction";
ResponseEntity<Boolean> response = template.postForEntity(url, walletTransactionRequest, Boolean.class);//calling
// walletService using template.postForEntity, four ways to do this 1.using restTemplate 2.Eureka 3.feignClient 4.WebClient
            String content = Strings.EMPTY;
            if (response.getStatusCode().is2xxSuccessful()) {
                NotificationRequest senderNotificationRequest = new NotificationRequest();
                senderNotificationRequest.setTransactionStatus("SUCCESS");
                senderNotificationRequest.setAmount(transactionRequest.getAmount());
                senderNotificationRequest.setUserId(userId);
                senderNotificationRequest.setUserType("SENDER");
                content = mapper.writeValueAsString(senderNotificationRequest);
                kafkaTemplate.send("notification-topic", content);

            }
        }

    }
}
