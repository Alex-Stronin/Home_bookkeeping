package project.dao;


import java.time.LocalDate;
import java.util.Map;

public interface AllExpensesUser {

    int calculationExpensesUser(int userId, LocalDate initialDate, LocalDate endData);

    Map<String, Long> calculationExpensesUserByCategory(int userId, LocalDate initialDate, LocalDate endData);
}
