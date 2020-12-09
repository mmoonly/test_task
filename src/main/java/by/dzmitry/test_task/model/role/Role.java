package by.dzmitry.test_task.model.role;

import by.dzmitry.test_task.model.AEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles",schema = "PUBLIC")
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
public class Role extends AEntity {

    @Column(name = "name")
    private String name;
}