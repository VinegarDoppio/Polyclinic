package runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runner.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
