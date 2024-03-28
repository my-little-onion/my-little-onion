package mylittleonion.api.onion.repository;

import java.util.List;
import mylittleonion.common.entity.Onion;
import mylittleonion.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnionRepository extends JpaRepository<Onion, Long> {

  List<Onion> getOnionsByUser(User user);
}
