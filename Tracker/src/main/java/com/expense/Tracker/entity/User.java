package com.expense.Tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(nullable = false)
    private String fullname;

   @Column(nullable = false, unique = true)
    private String email;

   @Column(nullable = false)
    private String password;

}


