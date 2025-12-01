package com.expense.Tracker.dto;

import lombok.Data;

@Data
public class ExpensesRequest {
    private String category;
    private String reason;
    private Long amount;
}
