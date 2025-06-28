package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;

@Entity
@Table(name="passwords")
@Getter @Setter
@NoArgsConstructor
public class Password {



    public Password(String hashedPassword){
        this.password = hashedPassword;}


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;


}
