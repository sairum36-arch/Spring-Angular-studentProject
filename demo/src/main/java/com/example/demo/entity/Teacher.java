package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="teachers")
public class Teacher {
    public Teacher(){

    }
    public Teacher(String fio, String group, String phoneNumber, User user){
        this.fio = fio;
        this.group = group;
        this.phoneNumber = phoneNumber;
        this.user = user;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fio;
    @Column(name="teacher_group")
    private String group;
    private String phoneNumber;
   //TODO: убрать jsonignore (сделано)
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public String getGroup(){
        return this.group;
    }
}
