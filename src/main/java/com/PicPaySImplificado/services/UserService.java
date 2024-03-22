package com.PicPaySImplificado.services;

import com.PicPaySImplificado.domain.User.User;
import com.PicPaySImplificado.domain.User.UserType;
import com.PicPaySImplificado.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepositories repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUsertype()== UserType.MERCAHNT){
            throw new Exception("usuario Logista não esta autorizado realizar transações");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserbyId(Long id) throws Exception {
        return this.repository.findUseById(id).orElseThrow(()-> new Exception("usuario não encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
