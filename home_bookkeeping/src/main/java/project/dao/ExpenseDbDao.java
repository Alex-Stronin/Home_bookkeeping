package project.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import project.model.Expense;

import java.time.LocalDate;
import java.util.List;


public interface ExpenseDbDao extends CrudRepository<Expense, Integer> {

    List<ExpenseAmount> findByDateBetween(LocalDate initialDate, LocalDate endDate);

    List<ExpenseAmount> findByUserIdAndDateBetween(int userId, LocalDate initialDate, LocalDate endDate);

    @Query("select expenseCategory.nameExpenseCategory as categoryName, sum(expenseAmount) as total \n" +
            "                  from Expense where date between ?2 and ?3 and user.id = ?1 \n" +
            "                  group by expenseCategory.nameExpenseCategory")
    List<TotalExpense> getExpensesUserByCategory(int userId, LocalDate initialDate, LocalDate endData);

    @Query("select expenseCategory.nameExpenseCategory as categoryName, sum(expenseAmount) as total \n" +
            "                  from Expense where date between ?1 and ?2 \n" +
            "                  group by expenseCategory.nameExpenseCategory")
    List<TotalExpense> getExpensesFamilyByCategory(LocalDate initialDate, LocalDate endData);

    @Modifying
    @Query("update Expense set expenseCategory.id = ?2 where expenseCategory.id = ?1")
    void rewritingExpenseId(int idCategoryToBeDeleted, int newIdCategory);
}