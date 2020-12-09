package by.dzmitry.test_task.dao.profile;

import by.dzmitry.test_task.model.profile.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileDao extends PagingAndSortingRepository<Profile, Integer> {
}
