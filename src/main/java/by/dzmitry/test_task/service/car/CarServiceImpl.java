package by.dzmitry.test_task.service.car;

import by.dzmitry.test_task.dao.car.CarDao;
import by.dzmitry.test_task.dao.numberplate.NumberplateDao;
import by.dzmitry.test_task.dao.profile.ProfileDao;
import by.dzmitry.test_task.exception.CustomException;
import by.dzmitry.test_task.model.car.Car;
import by.dzmitry.test_task.model.numberplate.Numberplate;
import by.dzmitry.test_task.model.profile.Profile;
import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.NumberplateDto;
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
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private NumberplateDao numberplateDao;

    @Autowired
    private ProfileDao profileDao;

    @Override
    public void addCar(String brand, Integer numberplateId) {
        Optional<Numberplate> numberplate = numberplateDao.findById(numberplateId);
        if (numberplate.isEmpty()) {
            throw new CustomException("Numberplate not found");
        }
        Car car = new Car(brand, numberplate.get());
        numberplateDao.save(numberplate.get());
        carDao.save(car);
    }

    @Override
    public void deleteCar(Integer carId) {
        Optional<Car> car = carDao.findById(carId);
        if (car.isEmpty()) {
            throw new CustomException("Car not found");
        }
        carDao.deleteById(carId);
    }

    @Override
    public void modifyCar(Integer carId, String brand, Integer numberplateId, List<Integer> profilesId) {
        Optional<Car> car = carDao.findById(carId);
        if (car.isEmpty()) {
            throw new CustomException("Car not found");
        }
        if (!"none".equals(brand)) {
            car.get().setBrand(brand);
        }
        if (numberplateId != 0) {
            Optional<Numberplate> numberplate = numberplateDao.findById(numberplateId);
            if (numberplate.isEmpty()) {
                throw new CustomException("Numberplate not found");
            }
            car.get().setNumberplate(numberplate.get());
        }
        if (!profilesId.isEmpty()) {
            List<Profile> profiles = new ArrayList<>();
            for (Integer profileId : profilesId) {
                profiles.add(profileDao.findById(profileId).get());
            }
            car.get().setProfiles(profiles);
        }
        carDao.save(car.get());
    }

    @Override
    public List<CarDto> getAllCars(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Car> pagedResult = carDao.findAll(paging);
        if (pagedResult.hasContent()) {
            return convertCarsListToDto(pagedResult.getContent());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public NumberplateDto getNumberplateByCar(Integer carId) {
        Optional<Car> car = carDao.findById(carId);
        if (car.isEmpty()) {
            throw new CustomException("Car not found");
        }
        return ModelDtoMapper.convertToNumberplateDto(car.get().getNumberplate());
    }

    @Override
    public List<ProfileDto> getProfilesByCar(Integer carId) {
        Optional<Car> car = carDao.findById(carId);
        if (car.isEmpty()) {
            throw new CustomException("Car not found");
        }
        List<Profile> profiles = car.get().getProfiles();
        if (profiles.isEmpty()) {
            throw new CustomException("No added profiles for this car");
        }
        return convertProfilesListToDto(profiles);
    }

    @Override
    public CarDto getCarById(Integer id) {
        Optional<Car> car = carDao.findById(id);
        if (car.isEmpty()) {
            throw new CustomException("Car not found");
        }
        return ModelDtoMapper.convertToCarDto(car.get());
    }

    private List<CarDto> convertCarsListToDto(List<Car> cars) {
        List<CarDto> carDto = new ArrayList<>();
        for (Car car : cars) {
            carDto.add(ModelDtoMapper.convertToCarDto(car));
        }
        return carDto;
    }

    private List<ProfileDto> convertProfilesListToDto(List<Profile> profiles) {
        List<ProfileDto> profileDto = new ArrayList<>();
        for (Profile profile : profiles) {
            profileDto.add(ModelDtoMapper.convertToProfileDto(profile));
        }
        return profileDto;
    }
}
