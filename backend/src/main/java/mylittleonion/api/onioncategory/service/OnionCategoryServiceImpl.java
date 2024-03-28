package mylittleonion.api.onioncategory.service;

import jakarta.transaction.Transactional;
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
}
