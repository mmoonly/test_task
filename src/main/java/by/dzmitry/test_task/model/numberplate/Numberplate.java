package by.dzmitry.test_task.model.numberplate;

import by.dzmitry.test_task.model.AEntity;
import by.dzmitry.test_task.model.car.Car;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "number_plates", schema = "PUBLIC")
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
public class Numberplate extends AEntity {

    @Column(name = "region")
    private Integer region;

    @Column(name = "series")
    private String series;

    @Column(name = "value")
    private String value;

    @OneToOne(mappedBy = "numberplate")
    private Car car;

    public Numberplate(Integer region, String series, String value) {
        this.region = region;
        this.series = series;
        this.value = value;
    }
}
