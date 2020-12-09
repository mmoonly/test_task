package by.dzmitry.test_task.model.car;

import by.dzmitry.test_task.model.AEntity;
import by.dzmitry.test_task.model.numberplate.Numberplate;
import by.dzmitry.test_task.model.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "number_plate_id")
    private Numberplate numberplate;

    @JsonIgnore
    @ManyToMany(mappedBy = "cars")
    private List<Profile> profiles;

    public Car(String brand, Numberplate numberplate) {
        this.brand = brand;
        this.numberplate = numberplate;
    }
}
