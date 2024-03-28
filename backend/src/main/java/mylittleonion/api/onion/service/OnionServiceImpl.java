package mylittleonion.api.onion.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.CreateOnionResponse;
import mylittleonion.api.onion.dto.GetOnionResponse;
import mylittleonion.api.onion.repository.OnionRepository;
import mylittleonion.api.onioncategory.service.OnionCategoryService;
import mylittleonion.api.user.service.UserService;
import mylittleonion.common.entity.Onion;
import mylittleonion.common.entity.OnionCategory;
import mylittleonion.common.entity.User;
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

  @Override
  public GetOnionResponse getOnion(Long userId, Integer onionNumber) {
    User user = userService.getUserById(userId);
    List<Onion> onionsByUser = onionRepository.getOnionsByUser(user);
    Onion onion = onionsByUser.get(onionNumber);
    OnionCategory onionCategory = onion.getOnionCategory();

    return GetOnionResponse.createGetOnionResponse(onion, onionCategory);
  }

  @Override
  public void deleteOnion(Long userId, Long onionId) {
    User user = userService.getUserById(userId);
    Onion onion = onionRepository.findById(onionId).orElseThrow();
    if (onion.getUser().getId().equals(userId)) {
      onionRepository.delete(onion);
    }
  }
}
