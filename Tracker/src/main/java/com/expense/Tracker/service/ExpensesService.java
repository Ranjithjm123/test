package com.expense.Tracker.service;

import com.expense.Tracker.config.JwtFilter;
import com.expense.Tracker.dto.ExpensesResponse;
import com.expense.Tracker.dto.ExpensesRequest;
import com.expense.Tracker.entity.Expense;
import com.expense.Tracker.entity.User;
import com.expense.Tracker.repository.ExpensesRepository;
import com.expense.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private UserRepository userRepository;

    public String addExpense(ExpensesRequest request) {
        Long userId = JwtFilter.getCurrentUserId();

        if (userId == null) {
            throw new RuntimeException("User not authenticated");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Expense expense = new Expense();
        expense.setCategory(request.getCategory());
        expense.setReason(request.getReason());
        expense.setAmount(request.getAmount());
        expense.setUser(user);

        expensesRepository.save(expense);

        return "Expense added successfully";
    }

    public List<ExpensesResponse> getMyExpenses() {
        Long userId = JwtFilter.getCurrentUserId();

        if (userId == null) {
            throw new RuntimeException("User not authenticated");
        }

        // Fetch expenses and map to DTO to avoid circular reference
        return expensesRepository.findByUserId(userId)
                .stream()
                .map(expense -> new ExpensesResponse(
                        expense.getEid(),
                        expense.getCategory(),
                        expense.getReason(),
                        expense.getAmount()
                ))
                .collect(Collectors.toList());
    }
}