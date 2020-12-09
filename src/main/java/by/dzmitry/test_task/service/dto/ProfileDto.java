package by.dzmitry.test_task.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileDto extends EntityDto {

    private String name;

    private String surname;
}
