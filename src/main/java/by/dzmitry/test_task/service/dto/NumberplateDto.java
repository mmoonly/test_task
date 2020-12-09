package by.dzmitry.test_task.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NumberplateDto extends EntityDto {

    private Integer region;

    private String series;

    private String value;
}
