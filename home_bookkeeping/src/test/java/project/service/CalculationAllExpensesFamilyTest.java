package project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllExpensesFamilyTest {

    @Autowired
    CalculationAllExpensesFamily calculationAllExpensesFamily;


    @Test
    void test_method_calculationExpensesFamily() {
        int result = 163200;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        Assertions.assertEquals(result, calculationAllExpensesFamily.calculationExpensesFamily(startPeriod, endPeriod));
    }

    @Test
    void test_method_calculationExpensesFamilyByCategory() {
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        Map<String, Long> list = Map.of("коммунальные платежи", 18000L,
                "расходы на питание", 17000L,
                "покупки непродовольственных товаров", 69000L,
                "транспортные расходы", 4500L,
                "расходы на мобильную связь и интернет", 1700L,
                "покупка лекарственных средств", 13000L,
                "непредвиденные расходы", 40000L);
        Assertions.assertEquals(list, calculationAllExpensesFamily.calculationExpensesFamilyByCategory(startPeriod, endPeriod));
    }
}
