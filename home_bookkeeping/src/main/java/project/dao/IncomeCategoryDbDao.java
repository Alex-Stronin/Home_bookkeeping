package project.dao;

import org.springframework.data.repository.CrudRepository;
import project.model.IncomeCategory;

public interface IncomeCategoryDbDao extends CrudRepository<IncomeCategory, Integer> {
    IncomeCategory findBySourceIncomeCategory(String sourceIncomeCategory);

    int deleteBySourceIncomeCategory(String sourceIncomeCategory);
}
