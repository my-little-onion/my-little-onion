package mylittleonion.api.onioncategory.repository;

import mylittleonion.common.entity.OnionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnionCategoryRepository extends JpaRepository<OnionCategory, Long> {

}
