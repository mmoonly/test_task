package by.dzmitry.test_task.controller.numberplate;

import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.NumberplateDto;
import by.dzmitry.test_task.service.numberplate.NumberplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/numberplates")
@Api(tags = "Numberplates")
public class NumberplateController {

    @Autowired
    private NumberplateService numberplateService;

    @PostMapping
    @ApiOperation("Used to add numberplates.")
    public void addNumberplate(@RequestParam Integer region, @RequestParam String series, @RequestParam String value) {
        numberplateService.addNumberplate(region, series, value);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Used to modify numberplates.")
    public void modifyNumberplate(@PathVariable Integer id,
                                  @RequestParam(defaultValue = "-1") Integer region,
                                  @RequestParam(defaultValue = "none") String series,
                                  @RequestParam(defaultValue = "none") String value) {
        numberplateService.modifyNumberplate(id, region, series, value);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Used to delete numberplates.")
    public void deleteNumberplate(@PathVariable Integer id) {
        numberplateService.deleteNumberplate(id);
    }

    @GetMapping
    @ApiOperation("Used to get numberplates.")
    public ResponseEntity<List<NumberplateDto>> getAllCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(numberplateService.getAllNumberPlates(pageNo, pageSize, sortBy));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Use to get numberplate by id.")
    public ResponseEntity<NumberplateDto> getNumberplateById(@PathVariable Integer id) {
        return ResponseEntity.ok(numberplateService.getNumberplateById(id));
    }

    @GetMapping("/{id}/car")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Used to get car by numberplate.")
    public ResponseEntity<CarDto> getCarByNumberplate(@PathVariable Integer id) {
        return ResponseEntity.ok(numberplateService.getCarByNumberplate(id));
    }

}
