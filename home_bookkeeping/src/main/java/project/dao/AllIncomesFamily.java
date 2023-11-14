package project.dao;

import java.time.LocalDate;
import java.util.Map;

public interface AllIncomesFamily {

    int calculationIncomesFamily(LocalDate startDate, LocalDate endData);

    Map<String, Long> calculationSourceIncomeByCategory(LocalDate startDate, LocalDate endData);
}
