package by.dzmitry.test_task.service.car;

import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.NumberplateDto;
import by.dzmitry.test_task.service.dto.ProfileDto;

import java.util.List;

public interface CarService {

    void addCar(String brand, Integer numberplateId);

    void deleteCar(Integer carId);

    void modifyCar(Integer carId, String brand, Integer numberplateId, List<Integer> profilesId);

    List<CarDto> getAllCars(Integer pageNo, Integer pageSize, String sortBy);

    NumberplateDto getNumberplateByCar(Integer carId);

    List<ProfileDto> getProfilesByCar(Integer carId);

    CarDto getCarById(Integer id);
}
