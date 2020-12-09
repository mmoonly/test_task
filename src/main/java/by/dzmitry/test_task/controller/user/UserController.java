package by.dzmitry.test_task.controller.user;

import by.dzmitry.test_task.model.user.User;
import by.dzmitry.test_task.service.user.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Api(tags = "Users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    @ApiOperation("Used to add users.")
    public void registerUser(@RequestParam String login, String password){
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        userService.save(user);
    }

    @PostMapping("/admin")
    @ApiOperation("Used to add admins.")
    public void registerAdmin(@RequestParam String login, String password) {
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        userService.saveAdmin(user);
    }

    @GetMapping("/hello")
    public ResponseEntity<List<User>> getSome() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
