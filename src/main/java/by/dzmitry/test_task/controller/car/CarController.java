package by.dzmitry.test_task.controller.car;

import by.dzmitry.test_task.service.car.CarService;
import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.NumberplateDto;
import by.dzmitry.test_task.service.dto.ProfileDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@Api(tags = "Cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    @ApiOperation("Used to add cars.")
    public void addCar(@RequestParam String brand, @RequestParam Integer numberplateId) {
        carService.addCar(brand, numberplateId);
    }

    @PutMapping("/{id}")
    @ApiOperation("Used to modify cars.")
    public void modifyCars(@PathVariable Integer id,
                           @RequestParam(defaultValue = "none") String brand,
                           @RequestParam(defaultValue = "0") Integer numberplateId,
                           @RequestParam(defaultValue = "") List<Integer> profilesId) {
        carService.modifyCar(id, brand, numberplateId, profilesId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Used to delete cars.")
    public void deleteCars(@PathVariable Integer id) {
        carService.deleteCar(id);
    }

    @GetMapping
    @ApiOperation("Used to get cars.")
    public ResponseEntity<List<CarDto>> getAllCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(carService.getAllCars(pageNo, pageSize, sortBy));
    }

    @GetMapping("/{id}")
    @ApiOperation("Use to get car by id.")
    public ResponseEntity<CarDto> getCarById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping("/{id}/numberplate")
    @ApiOperation("Used to get numberplate by car.")
    public ResponseEntity<NumberplateDto> getNumberplateByCar(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.getNumberplateByCar(id));
    }

    @GetMapping("/{id}/profiles")
    @ApiOperation("Used to get profiles by car.")
    public ResponseEntity<List<ProfileDto>> getProfilesByCar(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.getProfilesByCar(id));
    }
}
