package by.dzmitry.test_task.model.profile;

import by.dzmitry.test_task.model.AEntity;
import by.dzmitry.test_task.model.car.Car;
import by.dzmitry.test_task.model.city.City;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profiles", schema = "PUBLIC")
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
public class Profile extends AEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "profiles_cars",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;

    public Profile(String name, String surname, City city) {
        this.name = name;
        this.surname = surname;
        this.city = city;
    }
}
