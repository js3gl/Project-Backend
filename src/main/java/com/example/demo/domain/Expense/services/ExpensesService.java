package com.example.demo.domain.Expense.services;

import com.example.demo.domain.Expense.models.Expense;
import com.example.demo.domain.core.exceptions.ResourceCreationException;
import com.example.demo.domain.core.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ExpensesService {
    Expense createExpense(Expense expense) throws ResourceCreationException;
    Expense getExpenseById(Long id) throws ResourceNotFoundException;
    List<Expense> getAll();
    Expense updateExpenses(Long id, Expense expenseDetails) throws ResourceNotFoundException;
    Boolean deleteExpense(Long id) throws ResourceNotFoundException;
    List<Expense> filterExpensesByLastName(String lastName);
}
