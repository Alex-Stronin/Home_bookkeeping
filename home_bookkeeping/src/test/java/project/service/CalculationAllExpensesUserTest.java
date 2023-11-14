package project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllExpensesUserTest {

    @Autowired
    CalculationAllExpensesUser calculationAllExpensesUser;

    @Test
    void test_method_calculationExpensesUser() {
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        int result = 19000;
        Assertions.assertEquals(result, calculationAllExpensesUser.calculationExpensesUser(userId, startPeriod, endPeriod));
    }

    @Test
    void test_method_calculationExpensesUserByCategory() {
        int userId = 2;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        Map<String, Long> list = Map.of("коммунальные платежи", 3000L,
                "расходы на питание", 5000L,
                "покупки непродовольственных товаров", 10000L,
                "транспортные расходы", 100L,
                "расходы на мобильную связь и интернет", 300L,
                "покупка лекарственных средств", 600L);
        Assertions.assertEquals(list, calculationAllExpensesUser.calculationExpensesUserByCategory(userId, startPeriod, endPeriod));
    }
}
