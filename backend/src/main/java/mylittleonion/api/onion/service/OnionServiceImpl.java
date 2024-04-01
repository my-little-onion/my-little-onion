package mylittleonion.api.onion.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.categorycount.repository.CategoryCountRepository;
import mylittleonion.api.categorycount.service.CategoryCountService;
import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.GetOnionBookResponse;
import mylittleonion.api.onion.dto.GetOnionResponse;
import mylittleonion.api.onion.repository.OnionRepository;
import mylittleonion.api.onioncategory.repository.OnionCategoryRepository;
import mylittleonion.api.onioncategory.service.OnionCategoryService;
import mylittleonion.api.user.service.UserService;
import mylittleonion.api.voice.dto.ChatGPTResponse;
import mylittleonion.api.voice.repository.VoiceRepository;
import mylittleonion.chatGPT.dto.PromptResponseDto;
import mylittleonion.common.entity.CategoryCount;
import mylittleonion.common.entity.Onion;
import mylittleonion.common.entity.OnionCategory;
import mylittleonion.common.entity.User;
import mylittleonion.common.entity.Voice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OnionServiceImpl implements OnionService {

  private final OnionRepository onionRepository;
  private final OnionCategoryService onionCategoryService;
  private final UserService userService;
  private final VoiceRepository voiceRepository;
  private final OnionCategoryRepository onionCategoryRepository;
  private final CategoryCountService categoryCountService;
  private final CategoryCountRepository categoryCountRepository;

  @Override
  public void createOnion(Long userId, CreateOnionRequest createOnionRequest) {
    Onion newOnion = Onion.makeNewOnion(createOnionRequest.getOnionName(),
        userService.getUserById(userId),
        onionCategoryService.getOnionCategoryById(1L));
    onionRepository.save(newOnion);
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
  @Transactional
  public PromptResponseDto evolveOnion(Long onionId, String speech,
      ChatGPTResponse chatGPTResponse) {

    Onion onion = onionRepository.findById(onionId).orElseThrow();
    Voice voice = Voice.createVoice(speech, onion);
    voiceRepository.save(voice);

    OnionCategory nowCategory = onion.getOnionCategory();
    Integer nowLevel = nowCategory.getLevel();
    Integer nowGroup = nowCategory.getGroup();
//    log.info(String.valueOf(nowLevel));
//    log.info(String.valueOf(nowGroup));

    if (nowCategory.getIsFinal()) {
      return new PromptResponseDto(false);
    }

    if (nowLevel == 0) {
      // CategoryCount 저장하기
      OnionCategory category = onionCategoryService.getOnionCategoryByCategoryName(
          chatGPTResponse.getPrimary());
      CategoryCount categoryCount = CategoryCount.createCategoryCount(onion, category.getGroup(),
          category.getId());
      categoryCountRepository.save(categoryCount);
    } else if (nowLevel == 1) {
      OnionCategory category = onionCategoryService.getOnionCategoryByCategoryName(
          chatGPTResponse.getSecondary());
      if (category.getGroup() != nowGroup) {
        return new PromptResponseDto(false);
      }

      CategoryCount categoryCount = CategoryCount.createCategoryCount(onion, category.getGroup(),
          category.getId());
      categoryCountRepository.save(categoryCount);
    } else if (nowLevel == 2) {
      if (chatGPTResponse.getTertiary() == null) {
        return new PromptResponseDto(false);
      }
      OnionCategory category = onionCategoryService.getOnionCategoryByCategoryName(
          chatGPTResponse.getTertiary());
      if (category.getGroup() != nowGroup) {
        return new PromptResponseDto(false);
      }

      CategoryCount categoryCount = CategoryCount.createCategoryCount(onion, category.getGroup(),
          category.getId());
      categoryCountRepository.save(categoryCount);
    }

    //진화 알고리즘
    List<CategoryCount> categoryCounts = categoryCountService.getCategoryCountsByOnionId(onionId);
    boolean flag = false;

    if (nowLevel == 0) {
      int[] countGroup = new int[8];
      for (CategoryCount categoryCount : categoryCounts) {
        if (++countGroup[categoryCount.getGroupId()] == 3) {
          onion.changeCategory(
              onionCategoryRepository.getReferenceById(categoryCount.getCategoryId()));
          onionRepository.save(onion);
          flag = true;
          break;
        }
      }
      if (flag) {
        for (CategoryCount categoryCount : categoryCounts) {
          categoryCountRepository.deleteByCategoryIdAndOnionId(categoryCount.getCategoryId(),
              onionId);
        }
      }
    } else { //현재 레벨이 1이상일때
      flag = false;
      HashMap<Long, Integer> hm = new HashMap<>();
      for (CategoryCount categoryCount : categoryCounts) {
        Long cId = categoryCount.getCategoryId();
        hm.put(cId, hm.getOrDefault(cId, 0) + 1);
        if (hm.get(cId) == 3) {
          onion.changeCategory(
              onionCategoryRepository.getReferenceById(cId));
          onionRepository.save(onion);
          flag = true;
          break;
        }
      }

      if (flag) {
        for (CategoryCount categoryCount : categoryCounts) {
          categoryCountRepository.deleteByCategoryIdAndOnionId(categoryCount.getCategoryId(),
              onionId);
        }
      }

    }
    return new PromptResponseDto(flag);
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
