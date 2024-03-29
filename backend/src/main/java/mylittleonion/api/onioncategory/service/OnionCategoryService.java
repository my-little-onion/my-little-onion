package mylittleonion.api.onioncategory.service;

import java.util.List;
import mylittleonion.common.entity.OnionCategory;

public interface OnionCategoryService {

  OnionCategory getOnionCategoryById(Long onionCategoryId);

  List<OnionCategory> getAllOnionCategory();
}
