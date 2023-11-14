package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.User;

public interface UserDbDao extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}