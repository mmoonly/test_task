package by.dzmitry.test_task.dao.car;

import by.dzmitry.test_task.model.car.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarDao extends PagingAndSortingRepository<Car, Integer> {
}
