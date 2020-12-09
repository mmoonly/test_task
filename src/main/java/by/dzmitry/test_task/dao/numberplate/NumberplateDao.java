package by.dzmitry.test_task.dao.numberplate;

import by.dzmitry.test_task.model.numberplate.Numberplate;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NumberplateDao extends PagingAndSortingRepository<Numberplate, Integer> {
}
