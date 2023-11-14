package project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import project.model.User;

import java.math.BigDecimal;

public interface FamilyStartingCapitalDbDao extends CrudRepository<User, Integer> {

    @Query("select sum(startingCapital) from User")
    BigDecimal getFamilyStartingCapital();
}
