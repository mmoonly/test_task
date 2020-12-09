package by.dzmitry.test_task.controller.profile;

import by.dzmitry.test_task.service.dto.CarDto;
import by.dzmitry.test_task.service.dto.CityDto;
import by.dzmitry.test_task.service.dto.ProfileDto;
import by.dzmitry.test_task.service.profile.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@Api(tags = "Profiles")
public class ProfilesController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    @ApiOperation("Used to add profiles.")
    public void addProfile(@RequestParam String name, @RequestParam String surname, @RequestParam Integer cityId) {
        profileService.addProfile(name, surname, cityId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Used to modify profiles.")
    public void modifyProfile(@PathVariable Integer id,
                              @RequestParam(defaultValue = "none") String name,
                              @RequestParam(defaultValue = "none") String surname,
                              @RequestParam(defaultValue = "0") Integer cityId) {
        profileService.modifyProfile(id, name, surname, cityId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Used to delete profiles.")
    public void deleteProfile(@PathVariable Integer id) {
        profileService.deleteProfile(id);
    }

    @GetMapping
    @ApiOperation("Used to get profiles.")
    public ResponseEntity<List<ProfileDto>> getAllProfiles(@RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(profileService.getAllProfiles(pageNo, pageSize, sortBy));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Use to get profile by id.")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping("/{id}/cars")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Used to get cars by profile.")
    public ResponseEntity<List<CarDto>> getCarsByProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.getCarsByProfile(id));
    }

    @GetMapping("/{id}/city")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Used to get city by profile.")
    public ResponseEntity<CityDto> getCityByProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.getCityByProfile(id));
    }

}
