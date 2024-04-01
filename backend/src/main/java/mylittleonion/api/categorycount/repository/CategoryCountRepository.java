package mylittleonion.api.categorycount.repository;

import java.util.List;
import mylittleonion.common.entity.CategoryCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface CategoryCountRepository extends JpaRepository<CategoryCount, Long> {

  @Modifying
  @Transactional
  @Query("DELETE FROM CategoryCount cc WHERE cc.categoryId = ?1 AND cc.onion.id = ?2")
  void deleteByCategoryIdAndOnionId(long categoryId, long onionId);

  List<CategoryCount> findByOnion_Id(Long onionId);
}
