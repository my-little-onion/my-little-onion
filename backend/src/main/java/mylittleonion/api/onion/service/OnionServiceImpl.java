package mylittleonion.api.onion.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.CreateOnionResponse;
import mylittleonion.api.onion.repository.OnionRepository;
import mylittleonion.api.onioncategory.service.OnionCategoryService;
import mylittleonion.api.user.service.UserService;
import mylittleonion.common.entity.Onion;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OnionServiceImpl implements OnionService {

  private final OnionRepository onionRepository;
  private final OnionCategoryService onionCategoryService;
  private final UserService userService;

  @Override
  public CreateOnionResponse createOnion(Long userId, CreateOnionRequest createOnionRequest) {
    Onion newOnion = Onion.makeNewOnion(createOnionRequest.getOnionName(),
        userService.getUserById(userId),
        onionCategoryService.getOnionCategoryById(1L));
    onionRepository.save(newOnion);
    return new CreateOnionResponse();
  }
}
