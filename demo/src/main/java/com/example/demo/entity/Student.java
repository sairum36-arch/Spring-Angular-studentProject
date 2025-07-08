package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="students")
public class Student {
    public Student() {}

    public Student(String fio, String group, String phoneNumber, User user ){
        this.fio = fio;
        this.group = group;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fio;
    @Column(name="group_of_students")
    private  String group;
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;





}
