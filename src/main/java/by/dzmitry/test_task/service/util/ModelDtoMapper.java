package by.dzmitry.test_task.service.util;

import by.dzmitry.test_task.model.car.Car;
import by.dzmitry.test_task.model.city.City;
import by.dzmitry.test_task.model.numberplate.Numberplate;
import by.dzmitry.test_task.model.profile.Profile;
import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.CityDto;
import by.dzmitry.test_task.service.dto.NumberplateDto;
import by.dzmitry.test_task.service.dto.ProfileDto;

public class ModelDtoMapper {

    public static CarDto convertToCarDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setBrand(car.getBrand());
        return carDto;
    }

    public static ProfileDto convertToProfileDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setName(profile.getName());
        profileDto.setSurname(profile.getSurname());
        return profileDto;
    }

    public static CityDto convertToCityDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        return cityDto;
    }

    public static NumberplateDto convertToNumberplateDto(Numberplate numberplate) {
        NumberplateDto numberplateDto = new NumberplateDto();
        numberplateDto.setId(numberplate.getId());
        numberplateDto.setRegion(numberplate.getRegion());
        numberplateDto.setSeries(numberplate.getSeries());
        numberplateDto.setValue(numberplate.getValue());
        return numberplateDto;
    }
}
