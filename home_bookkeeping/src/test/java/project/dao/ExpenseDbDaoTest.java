package project.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ExpenseDbDaoTest {

    @Autowired
    ExpenseDbDao expenseDbDao;


    @Test
    void test_categories_are_mapped() {

        Assertions.assertDoesNotThrow(() -> expenseDbDao.findAll());
    }

    @Test
    void test_method_findByDateBetween() {
        List<Integer> list = List.of(5000, 3000, 23000, 300, 600, 1000,
                3000, 5000, 10000, 100, 300, 600,
                4000, 1000, 100, 400, 400,
                6000, 8000, 36000, 4000, 400, 11000, 40000);
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        List<Integer> result = expenseDbDao.findByDateBetween(startPeriod, endPeriod).stream()
                .map(ExpenseAmount::getExpenseAmount).toList();
        Assertions.assertEquals(list, result);
    }

    @Test
    void test_method_findByUserIdAndDateBetween() {
        int userId = 1;
        List<Integer> list = List.of(5000, 3000, 23000, 300, 600, 1000);
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        List<Integer> result = expenseDbDao.findByUserIdAndDateBetween(userId, startPeriod, endPeriod).stream()
                .map(ExpenseAmount::getExpenseAmount).toList();
        Assertions.assertEquals(list, result);
    }
}
