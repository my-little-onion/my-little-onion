package mylittleonion.api.categorycount.service;

import java.util.List;
import mylittleonion.api.categorycount.repository.CategoryCountRepository;
import mylittleonion.common.entity.CategoryCount;
import org.springframework.stereotype.Service;

@Service
public class CategoryCountServiceImpl implements CategoryCountService {

  private CategoryCountRepository categoryCountRepository;

  @Override
  public List<CategoryCount> getCategoryCountsByOnionId(Long onionId) {
    // 주어진 onionId에 해당하는 CategoryCount 객체들의 리스트를 조회
    return categoryCountRepository.findByOnion_Id(onionId);
  }

  @Override
  public void deleteCategoryCountByCategoryIdAndOnionId(long categoryId, long onionId) {
    categoryCountRepository.deleteByCategoryIdAndOnionId(categoryId, onionId);
  }
}
