package mylittleonion.api.user.repository;

import java.util.Optional;
import mylittleonion.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByKakaoId(Integer kakaoId);
}
