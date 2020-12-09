package by.dzmitry.test_task.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CarDto extends EntityDto {

    private String brand;
}
