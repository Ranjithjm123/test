package com.expense.Tracker.controller;

import com.expense.Tracker.dto.ExpensesResponse;
import com.expense.Tracker.dto.ExpensesRequest;
import com.expense.Tracker.service.ExpensesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@SecurityRequirement(name = "Authorization")
@CrossOrigin(origins = "*")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @PostMapping("/add")
    public String addExpense(@RequestBody ExpensesRequest request) {
        return expensesService.addExpense(request);
    }

    @GetMapping("/my")
    public List<ExpensesResponse> myExpenses() {
        return expensesService.getMyExpenses();
    }
}