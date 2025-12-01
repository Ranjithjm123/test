package com.expense.Tracker.repository;

import com.expense.Tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
}