package mylittleonion.api.onion.repository;

import mylittleonion.common.entity.Onion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnionRepository extends JpaRepository<Onion, Long> {

}
