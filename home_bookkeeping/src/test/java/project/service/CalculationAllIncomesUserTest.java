package project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class CalculationAllIncomesUserTest {
    @Autowired
    CalculationAllIncomesUser calculationIncomesUser;

    @Test
    void test_calculationIncomesUser() {
        int userId = 3;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        int result = 27000;
        Assertions.assertEquals(result, calculationIncomesUser.calculationIncomesUser(userId, startPeriod, endPeriod));
    }

    @Test
    void test_method_calculationIncomesUserBySource() {
        int userId = 3;
        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        Map<String, Long> list = Map.of("стипендия", 10000L,
                "доходы от других источников", 17000L);
        Assertions.assertEquals(list, calculationIncomesUser.calculationIncomesUserBySource(userId, startPeriod, endPeriod));
    }
}
