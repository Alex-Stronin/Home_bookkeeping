package project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllIncomesFamilyTest {

    @Autowired
    CalculationAllIncomesFamily calculationIncomes;

    @Test
    void test_method_calculationIncomesFamily() {
        int result = 466750;
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        Assertions.assertEquals(result, calculationIncomes.calculationIncomesFamily(startDate, endDate));
    }

    @Test
    void test_method_calculationSourceIncomeByCategory() {
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        Map<String, Long> list = Map.of("заработная плата", 201000L,
                "премия", 150750L,
                "доходы от ценных бумаг", 10000L,
                "стипендия", 10000L,
                "доходы от предпринимательской  деятельности", 78000L,
                "доходы от других источников", 17000L);
        Assertions.assertEquals(list, calculationIncomes.calculationSourceIncomeByCategory(startPeriod, endPeriod));
    }
}