package by.dzmitry.test_task.controller.city;

import by.dzmitry.test_task.service.city.CityService;
import by.dzmitry.test_task.service.dto.CityDto;
import by.dzmitry.test_task.service.dto.ProfileDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@Api(tags = "Cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    @ApiOperation("Used to add cities.")
    public void addCity(@RequestParam String name) {
        cityService.addCity(name);
    }

    @PutMapping("/{id}")
    @ApiOperation("Used to modify cities.")
    public void modifyCars(@PathVariable Integer id,
                           @RequestParam(defaultValue = "none") String name) {
        cityService.modifyCity(id, name);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Used to delete cities.")
    public void deleteCars(@PathVariable Integer id) {
        cityService.deleteCity(id);
    }

    @GetMapping
    @ApiOperation("Used to get cities.")
    public ResponseEntity<List<CityDto>> getAllCities(@RequestParam(defaultValue = "0") Integer pageNo,
                                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                                      @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(cityService.getAllCities(pageNo, pageSize, sortBy));
    }

    @GetMapping("/{id}")
    @ApiOperation("Use to get city by id.")
    public ResponseEntity<CityDto> getCityById(@PathVariable Integer id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @GetMapping("/{id}/profiles")
    @ApiOperation("Used to get profiles by city.")
    public ResponseEntity<List<ProfileDto>> getProfilesByCar(@PathVariable Integer id) {
        return ResponseEntity.ok(cityService.getProfilesByCity(id));
    }
}
