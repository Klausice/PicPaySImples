package com.PicPaySImplificado.services;

import com.PicPaySImplificado.domain.User.User;
import com.PicPaySImplificado.domain.transaction.Transaction;
import com.PicPaySImplificado.dtos.TransactionDTO;
import com.PicPaySImplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class transactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserbyId(transaction.senderId());
        User receiver = this.userService.findUserbyId(transaction.receiverId());

        userService.validateTransaction(sender,transaction.value());

        boolean isAuthorized =this.authorizeTransaction(sender,transaction.value());
        if (!isAuthorized){
            throw new Exception("transação não autorizado");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if (authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("menssage");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }


}
