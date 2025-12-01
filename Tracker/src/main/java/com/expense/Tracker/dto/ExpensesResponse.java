package com.expense.Tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesResponse {
    private Long eid;
    private String category;
    private String reason;
    private Long amount;
}

// Note: Make sure this file is named ExpenseResponse.java (singular, not ExpensesResponse.java)