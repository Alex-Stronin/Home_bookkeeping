package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dao.FamilyBalance;
import project.dao.FamilyStartingCapitalDbDao;

import java.time.LocalDate;


@Component
public class CalculationFamilyBalance implements FamilyBalance {

    private final CalculationAllIncomesFamily amountAllIncomes;
    private final CalculationAllExpensesFamily amountAllExpenses;

    private FamilyStartingCapitalDbDao startingCapitalDao;

    @Autowired
    public CalculationFamilyBalance(CalculationAllIncomesFamily amountAllIncomes, CalculationAllExpensesFamily amountAllExpenses, FamilyStartingCapitalDbDao startingCapitalDao) {
        this.amountAllIncomes = amountAllIncomes;
        this.amountAllExpenses = amountAllExpenses;
        this.startingCapitalDao = startingCapitalDao;
    }

    @Override
    public int getBalance(LocalDate startDate, LocalDate endDate) {
        return startingCapitalDao.getFamilyStartingCapital().intValue() +
                amountAllIncomes.calculationIncomesFamily(startDate, endDate) - amountAllExpenses.calculationExpensesFamily(startDate, endDate);
    }
}
