package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dao.AllExpensesUser;
import project.dao.ExpenseAmount;
import project.dao.ExpenseDbDao;
import project.dao.TotalExpense;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalculationAllExpensesUser implements AllExpensesUser {

    private final ExpenseDbDao expenseDao;

    @Autowired
    public CalculationAllExpensesUser(ExpenseDbDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    @Override
    public int calculationExpensesUser(int userId, LocalDate initialDate, LocalDate endDate) {
        List<ExpenseAmount> listExpenses = expenseDao.findByUserIdAndDateBetween(userId, initialDate, endDate);
        return listExpenses.stream().map(ExpenseAmount::getExpenseAmount).reduce(0, Integer::sum);
    }


    @Override
    public Map<String, Long> calculationExpensesUserByCategory(int userId, LocalDate initialDate, LocalDate endData) {
        List<TotalExpense> list = expenseDao.getExpensesUserByCategory(userId, initialDate, endData);
        Map<String, Long> map = new HashMap<>();
        for (TotalExpense e : list) {
            map.put(e.getCategoryName(), e.getTotal());
        }
        return map;
    }
}
