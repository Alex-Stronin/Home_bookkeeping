package project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.model.Expense;
import project.model.ExpenseCategory;
import project.model.Income;
import project.model.IncomeCategory;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class RedactRecordsInDbTest {
    @Autowired
    RedactRecordsInDb redactRecordsInDb;
    @Autowired
    CalculationAllExpensesFamily calculationAllExpensesFamily;

    @Autowired
    CalculationAllIncomesFamily calculationAllIncomesFamily;

    @Test
    @Disabled
    void test_method_editingCategoryNamesExpense() {
        String currentName = "расходы на питание";
        String newName = "продукты питания";

        redactRecordsInDb.editingCategoryNamesExpense(currentName, newName);
        ExpenseCategory result = redactRecordsInDb.getExpenseCategoryList().stream()
                .filter(e -> e.getNameExpenseCategory().equals(newName)).findAny().orElseThrow();
        Assertions.assertEquals(newName, result.getNameExpenseCategory());

        int sumAllExpenses = result.getExpenses().stream().mapToInt(Expense::getExpenseAmount).sum();
        Assertions.assertEquals(17000, sumAllExpenses);
    }

    @Test
    @Disabled
    void test_method_editingCategoryNamesIncome() {
        String currentName = "доходы от ценных бумаг";
        String newName = "дивиденды";

        redactRecordsInDb.editingCategoryNamesIncome(currentName, newName);
        IncomeCategory result = redactRecordsInDb.getIncomeSourceList().stream()
                .filter(c -> c.getSourceIncomeCategory().equals(newName))
                .findAny().orElseThrow();

        Assertions.assertEquals(newName, result.getSourceIncomeCategory());

        int sumAllExpenses = result.getIncomes().stream().mapToInt(Income::getIncomeAmount).sum();
        Assertions.assertEquals(10000, sumAllExpenses);
    }

    @Test
    @Disabled
    void test_remove_Category_from_Db_for_expense_category() {

        String nameRemoveCategory = "покупки непродовольственных товаров";
        int idAnotherCategory = 7;
        redactRecordsInDb.removeExpenseCategory(nameRemoveCategory, idAnotherCategory);

        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        Map<String, Long> list = Map.of("коммунальные платежи", 18000L,
                "расходы на питание", 17000L,
                "транспортные расходы", 4500L,
                "расходы на мобильную связь и интернет", 1700L,
                "покупка лекарственных средств", 13000L,
                "непредвиденные расходы", 109000L);
        Assertions.assertEquals(list, calculationAllExpensesFamily.calculationExpensesFamilyByCategory(startPeriod, endPeriod));
    }

    @Test
    @Disabled
    void test_remove_Category_from_Db_for_income_source() {

        String nameRemoveCategory = "премия";
        int idAnotherCategory = 6;
        redactRecordsInDb.removeIncomeSource(nameRemoveCategory, idAnotherCategory);

        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 12, 31);
        Map<String, Long> list = Map.of("заработная плата", 201000L,
                "доходы от ценных бумаг", 10000L,
                "стипендия", 10000L,
                "доходы от предпринимательской  деятельности", 78000L,
                "доходы от других источников", 167750L);
        Assertions.assertEquals(list, calculationAllIncomesFamily.calculationSourceIncomeByCategory(startPeriod, endPeriod));
    }
}
