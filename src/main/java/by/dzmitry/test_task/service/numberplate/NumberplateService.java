package by.dzmitry.test_task.service.numberplate;

import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.NumberplateDto;

import java.util.List;

public interface NumberplateService {

    void addNumberplate(Integer region, String series, String value);

    void modifyNumberplate(Integer numberplateId, Integer region, String series, String value);

    void deleteNumberplate(Integer numberplateId);

    List<NumberplateDto> getAllNumberPlates(Integer pageNo, Integer pageSize, String sortBy);

    CarDto getCarByNumberplate(Integer numberplateId);

    NumberplateDto getNumberplateById(Integer id);
}
