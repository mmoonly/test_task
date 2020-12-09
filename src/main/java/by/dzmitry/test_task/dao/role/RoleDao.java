package by.dzmitry.test_task.dao.role;

import by.dzmitry.test_task.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
