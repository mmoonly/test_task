package by.dzmitry.test_task.model.user;

import by.dzmitry.test_task.model.AEntity;
import by.dzmitry.test_task.model.role.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "PUBLIC")
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
public class User extends AEntity {

    @Column(name = "login")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
