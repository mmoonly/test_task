package by.dzmitry.test_task.dao.city;

import by.dzmitry.test_task.model.city.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityDao extends PagingAndSortingRepository<City, Integer> {

    City findByName(String name);
}
