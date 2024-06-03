package com.example.demo.domain.Expense.services;

import com.example.demo.domain.Expense.models.Expense;
import com.example.demo.domain.Expense.repos.ExpensesRepo;
import com.example.demo.domain.core.exceptions.ResourceCreationException;
import com.example.demo.domain.core.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepo expensesRepo;

    public ExpensesServiceImpl(ExpensesRepo expensesRepo) {
        this.expensesRepo = expensesRepo;
    }

    @Override
    public Expense createExpense(Expense expense) throws ResourceCreationException {
        expense.setDateOfExpense(LocalDateTime.now());
        expense.setLastUpdatedDateOfExpense(LocalDateTime.now());
        expense.setDOO(false);
        expense.setCEO(false);
        expense.setRequesterSupervisor(false);
        try {
            return expensesRepo.save(expense);
        } catch (Exception e) {
            throw new ResourceCreationException("Error creating expense");
        }
    }

    @Override
    public Expense getExpenseById(Long id) throws ResourceNotFoundException {
        Expense expense = expensesRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No post with id: " + id));
        return expense;
    }

    @Override
    public List<Expense> getAll() {
        return expensesRepo.findAll();
    }

    //two programs with a cost  system where you can sim create expense and create
    // program tied with expense 1000 pens 500 dollars youth and adult program use
    // youth 250 adult 250

    @Override
    public Expense updateExpenses(Long id, Expense expensesDetails) throws ResourceNotFoundException {
        Expense expense = getExpenseById(id);
        expense.setLastUpdatedDateOfExpense(LocalDateTime.now());
        expense.setFirstName(expensesDetails.getFirstName());
        expense.setLastName(expensesDetails.getLastName());
        expense.setItems(expensesDetails.getItems());
        expense.setPurpose(expensesDetails.getPurpose());
        expense.setExpensePrograms(expensesDetails.getExpensePrograms());
        expense.setDateNeeded(expensesDetails.getDateNeeded());
        expense.setRequester(expensesDetails.isRequester());
        expense.setRequesterSupervisor(expensesDetails.isRequesterSupervisor());
        expense.setDOO(expensesDetails.isDOO());
        expense.setCEO(expensesDetails.isCEO());
        expense.setUserId(expensesDetails.getUserId());
        return expensesRepo.save(expense);
    }

    @Override
    public Boolean deleteExpense(Long id) throws ResourceNotFoundException {
        Expense expense = getExpenseById(id);
        expensesRepo.delete(expense);
        return true;
    }

    @Override
    public List<Expense> filterExpensesByLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            return getAll();
        }
        return expensesRepo.findByLastNameContainingIgnoreCase(lastName.trim());
    }

}
