package by.dzmitry.test_task.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}