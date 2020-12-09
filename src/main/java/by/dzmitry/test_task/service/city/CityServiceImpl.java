package by.dzmitry.test_task.service.city;

import by.dzmitry.test_task.dao.city.CityDao;
import by.dzmitry.test_task.exception.CustomException;
import by.dzmitry.test_task.model.city.City;
import by.dzmitry.test_task.model.profile.Profile;
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
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public void addCity(String name) {
        if (cityDao.findByName(name) != null) {
            throw new CustomException("Such city already exist");
        }
        cityDao.save(new City(name));
    }

    @Override
    public void modifyCity(Integer cityId, String name) {
        if ("none".equals(name)) {
            throw new CustomException("Nothing to change");
        }
        Optional<City> city = cityDao.findById(cityId);
        if (city.isEmpty()) {
            throw new CustomException("No such city");
        }
        city.get().setName(name);
        cityDao.save(city.get());
    }

    @Override
    public void deleteCity(Integer cityId) {
        Optional<City> city = cityDao.findById(cityId);
        if (city.isEmpty()) {
            throw new CustomException("No such city");
        }
        cityDao.deleteById(cityId);
    }

    @Override
    public List<CityDto> getAllCities(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<City> pagedResult = cityDao.findAll(paging);
        if (pagedResult.hasContent()) {
            return convertCitiesListToDto(pagedResult.getContent());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProfileDto> getProfilesByCity(Integer cityId) {
        Optional<City> city = cityDao.findById(cityId);
        if (city.isEmpty()) {
            throw new CustomException("No such city");
        }
        List<Profile> profiles = city.get().getProfiles();
        if (profiles.isEmpty()) {
            throw new CustomException("No added profiles for this city");
        }
        return convertProfilesListToDto(profiles);
    }

    @Override
    public CityDto getCityById(Integer id) {
        Optional<City> city = cityDao.findById(id);
        if (city.isEmpty()) {
            throw new CustomException("No such city");
        }
        return ModelDtoMapper.convertToCityDto(city.get());
    }

    private List<CityDto> convertCitiesListToDto(List<City> cities) {
        List<CityDto> cityDto = new ArrayList<>();
        for (City city : cities) {
            cityDto.add(ModelDtoMapper.convertToCityDto(city));
        }
        return cityDto;
    }

    private List<ProfileDto> convertProfilesListToDto(List<Profile> profiles) {
        List<ProfileDto> profileDto = new ArrayList<>();
        for (Profile profile : profiles) {
            profileDto.add(ModelDtoMapper.convertToProfileDto(profile));
        }
        return profileDto;
    }
}
