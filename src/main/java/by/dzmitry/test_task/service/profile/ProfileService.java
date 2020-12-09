package by.dzmitry.test_task.service.profile;

import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.CityDto;
import by.dzmitry.test_task.service.dto.ProfileDto;

import java.util.List;

public interface ProfileService {

    void addProfile(String name, String surname, Integer cityId);

    void modifyProfile(Integer id, String name, String surname, Integer cityId);

    void deleteProfile(Integer id);

    List<ProfileDto> getAllProfiles(Integer pageNo, Integer pageSize, String sortBy);

    List<CarDto> getCarsByProfile(Integer id);

    CityDto getCityByProfile(Integer id);

    ProfileDto getProfileById(Integer id);
}
