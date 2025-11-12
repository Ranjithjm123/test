package com.expense.Tracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegisterRequest {
    private String fullname;
    private String email;
    private String password;
}
