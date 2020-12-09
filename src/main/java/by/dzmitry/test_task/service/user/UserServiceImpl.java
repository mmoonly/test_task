package by.dzmitry.test_task.service.user;

import by.dzmitry.test_task.dao.role.RoleDao;
import by.dzmitry.test_task.dao.user.UserDao;
import by.dzmitry.test_task.exception.CustomException;
import by.dzmitry.test_task.model.role.Role;
import by.dzmitry.test_task.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        if (userDao.findByUsername(user.getUsername()) != null) {
            throw new CustomException("Such username already exist");
        }
        Role role = roleDao.findByName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public void saveAdmin(User user) {
        Role role = roleDao.findByName("ROLE_ADMIN");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsEntity().toUserDetails(userDao.findByUsername(name));
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }
}