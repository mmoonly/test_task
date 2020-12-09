package by.dzmitry.test_task.service.city;

import by.dzmitry.test_task.service.dto.CityDto;
import by.dzmitry.test_task.service.dto.ProfileDto;

import java.util.List;

public interface CityService {

    void addCity(String name);

    void modifyCity(Integer cityId, String name);

    void deleteCity(Integer cityId);

    List<CityDto> getAllCities(Integer pageNo, Integer pageSize, String sortBy);

    List<ProfileDto> getProfilesByCity(Integer cityId);

    CityDto getCityById(Integer id);
}
