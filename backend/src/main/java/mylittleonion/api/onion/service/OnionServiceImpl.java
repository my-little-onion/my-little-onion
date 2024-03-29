package mylittleonion.api.onion.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.CreateOnionResponse;
import mylittleonion.api.onion.dto.GetOnionBookResponse;
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
  public List<GetOnionResponse> getOnion(Long userId) {
    User user = userService.getUserById(userId);
    List<Onion> onionsByUser = onionRepository.getOnionsByUser(user);
    List<OnionCategory> onionCategories = onionsByUser.stream().map(Onion::getOnionCategory)
        .toList();

    ArrayList<GetOnionResponse> result = new ArrayList<>();
    for (int i = 0; i < onionsByUser.size(); i++) {
      if (onionsByUser.get(i).getVisible().equals(Boolean.FALSE)) {
        continue;
      }
      result.add(GetOnionResponse.createGetOnionResponse(onionsByUser.get(i),
          onionCategories.get(i)
      ));
    }

    return result;
  }

  @Override
  public void deleteOnion(Long userId, Long onionId) {
    User user = userService.getUserById(userId);
    Onion onion = onionRepository.findById(onionId).orElseThrow();
    if (onion.getUser().getId().equals(user.getId())) {
      onion.deleteOnion();
      onionRepository.save(onion);
    }
  }

  @Override
  public List<GetOnionBookResponse> getOnionBook(Long userId) {
    List<OnionCategory> allOnionCategory = onionCategoryService.getAllOnionCategory();
    List<Onion> onionsByUser = onionRepository.getOnionsByUser(userService.getUserById(userId));

    List<GetOnionBookResponse> result = new ArrayList<>();
    for (int i = 0; i < allOnionCategory.size(); i++) {
      boolean flag = false;
      for (int j = 0; j < onionsByUser.size(); j++) {
        if (onionsByUser.get(j).getVisible().equals(false)) {
          if (onionsByUser.get(j).getOnionCategory().equals(allOnionCategory.get(i))) {
            flag = true;
            break;
          }
        }
      }

      if (flag) {
        result.add(GetOnionBookResponse.createGetOnionBookResponse(allOnionCategory.get(i).getId(),
            Boolean.TRUE, allOnionCategory.get(i).getCategoryName(),
            allOnionCategory.get(i).getOnionDetail()));
      } else {
        result.add(GetOnionBookResponse.createGetOnionBookResponse(allOnionCategory.get(i).getId(),
            Boolean.FALSE, allOnionCategory.get(i).getCategoryName(),
            allOnionCategory.get(i).getOnionDetail()));
      }
    }

    return result;
  }
}
