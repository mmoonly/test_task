package by.dzmitry.test_task.model.car;

import by.dzmitry.test_task.model.AEntity;
import by.dzmitry.test_task.model.numberplate.Numberplate;
import by.dzmitry.test_task.model.profile.Profile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars", schema = "PUBLIC")
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
public class Car extends AEntity {

    @Column(name = "brand")
    private String brand;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "number_plate_id")
    private Numberplate numberplate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "profiles_cars",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private List<Profile> profiles;

    public Car(String brand, Numberplate numberplate) {
        this.brand = brand;
        this.numberplate = numberplate;
    }
}
