package com.expense.Tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = true)
    private String catagory;

    @Column(nullable = true)
    private String reason;

    @Column(nullable = false)
    private Long Amount;
}
