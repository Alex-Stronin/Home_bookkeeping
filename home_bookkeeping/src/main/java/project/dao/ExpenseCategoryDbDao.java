package project.dao;

import org.springframework.data.repository.CrudRepository;
import project.model.ExpenseCategory;


public interface ExpenseCategoryDbDao extends CrudRepository<ExpenseCategory, Integer> {
    ExpenseCategory findByNameExpenseCategory(String nameExpenseCategory);

    int deleteByNameExpenseCategory(String nameExpenseCategory);
}
