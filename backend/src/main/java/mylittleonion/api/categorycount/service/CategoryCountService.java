package mylittleonion.api.categorycount.service;

import java.util.List;
import mylittleonion.common.entity.CategoryCount;


public interface CategoryCountService {

  List<CategoryCount> getCategoryCountsByOnionId(Long onionId);

  void deleteCategoryCountByCategoryIdAndOnionId(long categoryId, long onionId);
}
