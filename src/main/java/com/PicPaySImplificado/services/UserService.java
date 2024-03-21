package com.PicPaySImplificado.services;

import com.PicPaySImplificado.domain.User.User;
import com.PicPaySImplificado.domain.User.UserType;
import com.PicPaySImplificado.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    private UserRepositories repository;
    @Autowired
    private void validadeTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUsertype() == UserType.MERCAHNT){
            throw new Exception("Usuário do tipo Lojista não esta autorizada realizar transferencias");
        }
        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo Insuficiente");
        }
    }
    public User findUserById(Long id) throws Exception {
        return repository.findUseById(id).orElseThrow(()->new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
