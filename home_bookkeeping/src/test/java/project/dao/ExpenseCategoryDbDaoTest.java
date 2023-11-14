package project.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ExpenseCategoryDbDaoTest {

    @Autowired
    ExpenseCategoryDbDao categoryDbDao;

    @Test
    void test_categories_are_mapped() {

        Assertions.assertDoesNotThrow(() -> categoryDbDao.findAll());
    }

    @Test
    void test_method_findByNameExpenseCategory() {
        String searchNameInTheDb = "транспортные расходы";
        String resultFromDb = categoryDbDao.findByNameExpenseCategory(searchNameInTheDb).getNameExpenseCategory();
        Assertions.assertEquals(searchNameInTheDb, resultFromDb);
    }

}
