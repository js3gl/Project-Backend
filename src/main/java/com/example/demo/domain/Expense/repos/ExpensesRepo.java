package com.example.demo.domain.Expense.repos;

import com.example.demo.domain.Expense.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepo extends JpaRepository<Expense, Long> {
    List<Expense> findByLastNameContainingIgnoreCase(String lastName);
}
