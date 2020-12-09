package by.dzmitry.test_task.model.city;

import by.dzmitry.test_task.model.AEntity;
import by.dzmitry.test_task.model.profile.Profile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cities", schema = "PUBLIC")
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
public class City extends AEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Profile> profiles;

    public City(String name) {
        this.name = name;
    }
}
