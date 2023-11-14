package project.dao;

import java.time.LocalDate;
import java.util.Map;

public interface AllIncomesUser {

    int calculationIncomesUser(int userId, LocalDate startDate, LocalDate endDate);

    Map<String, Long> calculationIncomesUserBySource(int userId, LocalDate startDate, LocalDate endDate);
}
