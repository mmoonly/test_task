package by.dzmitry.test_task.dao.user;

import by.dzmitry.test_task.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String name);
}
