package mylittleonion.api.onioncategory.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mylittleonion.api.onioncategory.repository.OnionCategoryRepository;
import mylittleonion.common.entity.OnionCategory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OnionCategoryServiceImpl implements OnionCategoryService {

  private final OnionCategoryRepository onionCategoryRepository;

  @Override
  public OnionCategory getOnionCategoryById(Long onionCategoryId) {
    return onionCategoryRepository.findById(onionCategoryId).orElseThrow();
  }


  @Override
  public OnionCategory getOnionCategoryByCategoryName(String categoryName) {
    return onionCategoryRepository.findByCategoryName(categoryName);
  }



  @Override
  public List<OnionCategory> getAllOnionCategory() {
    return onionCategoryRepository.findAll();
  }
}
