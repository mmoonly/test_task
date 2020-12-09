package by.dzmitry.test_task.service.profile;

import by.dzmitry.test_task.dao.city.CityDao;
import by.dzmitry.test_task.dao.profile.ProfileDao;
import by.dzmitry.test_task.exception.CustomException;
import by.dzmitry.test_task.model.car.Car;
import by.dzmitry.test_task.model.city.City;
import by.dzmitry.test_task.model.profile.Profile;
import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.CityDto;
import by.dzmitry.test_task.service.dto.ProfileDto;
import by.dzmitry.test_task.service.util.ModelDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private CityDao cityDao;

    @Override
    public void addProfile(String name, String surname, Integer cityId) {
        Optional<City> city = cityDao.findById(cityId);
        if (city.isEmpty()) {
            throw new CustomException("City not found");
        }
        Profile profile = new Profile(name, surname, city.get());
        cityDao.save(city.get());
        profileDao.save(profile);
    }

    @Override
    public void modifyProfile(Integer id, String name, String surname, Integer cityId) {
        Optional<Profile> profile = profileDao.findById(id);
        if (profile.isEmpty()) {
            throw new CustomException("Profile not found");
        }
        if (!"none".equals(name)) {
            profile.get().setName(name);
        }
        if (!"none".equals(surname)) {
            profile.get().setSurname(surname);
        }
        if (cityId != 0) {
            Optional<City> city = cityDao.findById(cityId);
            if (city.isEmpty()) {
                throw new CustomException("City not found");
            }
            profile.get().setCity(city.get());
        }
        profileDao.save(profile.get());
    }

    @Override
    public void deleteProfile(Integer id) {
        Optional<Profile> profile = profileDao.findById(id);
        if (profile.isEmpty()) {
            throw new CustomException("Profile not found");
        }
        profileDao.deleteById(id);
    }

    @Override
    public List<ProfileDto> getAllProfiles(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Profile> pagedResult = profileDao.findAll(paging);
        if (pagedResult.hasContent()) {
            return convertProfilesListToDto(pagedResult.getContent());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<CarDto> getCarsByProfile(Integer id) {
        Optional<Profile> profile = profileDao.findById(id);
        if (profile.isEmpty()) {
            throw new CustomException("Profile not found");
        }
        List<Car> cars = profile.get().getCars();
        if (cars.isEmpty()) {
            throw new CustomException("No added cars for this profile");
        }
        return convertCarsListToDto(cars);
    }

    @Override
    public CityDto getCityByProfile(Integer id) {
        Optional<Profile> profile = profileDao.findById(id);
        if (profile.isEmpty()) {
            throw new CustomException("Profile not found");
        }
        return ModelDtoMapper.convertToCityDto(profile.get().getCity());
    }

    @Override
    public ProfileDto getProfileById(Integer id) {
        Optional<Profile> profile = profileDao.findById(id);
        if (profile.isEmpty()) {
            throw new CustomException("Profile not found");
        }
        return ModelDtoMapper.convertToProfileDto(profile.get());
    }

    private List<ProfileDto> convertProfilesListToDto(List<Profile> profiles) {
        List<ProfileDto> profileDto = new ArrayList<>();
        for (Profile profile : profiles) {
            profileDto.add(ModelDtoMapper.convertToProfileDto(profile));
        }
        return profileDto;
    }

    private List<CarDto> convertCarsListToDto(List<Car> cars) {
        List<CarDto> carDto = new ArrayList<>();
        for (Car car : cars) {
            carDto.add(ModelDtoMapper.convertToCarDto(car));
        }
        return carDto;
    }
}
