package project.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IncomeCategoryDbDaoTest {

    @Autowired
    IncomeCategoryDbDao categoryDbDao;

    @Test
    void test_categories_are_mapped() {

        Assertions.assertDoesNotThrow(() -> categoryDbDao.findAll());
    }

    @Test
    void test_method_findBySourceIncomeCategory() {
        String searchNameInTheDb = "доходы от ценных бумаг";
        String resultFromDb = categoryDbDao.findBySourceIncomeCategory(searchNameInTheDb).getSourceIncomeCategory();
        Assertions.assertEquals(searchNameInTheDb, resultFromDb);
    }

}
