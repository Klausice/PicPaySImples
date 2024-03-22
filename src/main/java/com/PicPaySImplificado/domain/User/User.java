package com.PicPaySImplificado.domain.User;

import com.PicPaySImplificado.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name= "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fistName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private  UserType usertype;

    public User(UserDTO data){
        this.fistName = data.fistName();
        this.lastName = data.LastName();
        this.balance = data.balance();
        this.usertype = data.userType();
        this.password = data.password();
        this.email = data.email();
    }

}
