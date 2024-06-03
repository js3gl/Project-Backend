package com.example.demo.domain.Expense.controllers;

import com.example.demo.domain.Expense.models.Expense;
import com.example.demo.domain.Expense.services.ExpensesService;
import com.example.demo.domain.core.exceptions.ResourceCreationException;
import com.example.demo.domain.core.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpensesController {

    private final ExpensesService expensesService;

    @Autowired
    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Expense>> filterExpensesByLastName(@RequestParam String lastName) {
        List<Expense> expenses = expensesService.filterExpensesByLastName(lastName);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) throws ResourceCreationException {
        Expense createdExpense = expensesService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<List<Expense>> getAll(){
        List<Expense> expenses = expensesService.getAll();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Expense> getExpensesById(@PathVariable("id") Long id) {
        try {
            Expense expense = expensesService.getExpenseById(id);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Expense> updateExpenses(@PathVariable("id") Long id, @RequestBody Expense expenseDetails) {
        try {
            Expense updatedExpenses = expensesService.updateExpenses(id, expenseDetails);
            return new ResponseEntity<>(updatedExpenses, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") Long id) {
        try {
            expensesService.deleteExpense(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
