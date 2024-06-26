package mylittleonion.api.onion.service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
import mylittleonion.common.redis.RedisService;
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
  private final RedisService redisService;

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

    String makeVoiceListKey = "userId:" + userId + ":voiceCreateTime";
    Integer voiceNumber = 5;
    if (redisService.existsKey(makeVoiceListKey)) {
      List<String> list = redisService.getValuesForList(makeVoiceListKey);
      LocalDateTime now = LocalDateTime.now();
      for (String s : list) {
        // 'T' 문자를 처리하기 위해 패턴 수정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(s, formatter);

        LocalDateTime dateTimePlus10Minutes = dateTime.plusMinutes(10);

        if (!dateTimePlus10Minutes.isBefore(now) && !dateTimePlus10Minutes.isEqual(now)) {
          voiceNumber--;
        }
      }
    }
    log.info(Integer.toString(voiceNumber));

    String voiceNumberKey = "userId:" + userId + ":remainVoiceNumber";
    redisService.setValue(voiceNumberKey, Integer.toString(voiceNumber));

    log.info(redisService.getValues(voiceNumberKey));

    ArrayList<GetOnionResponse> result = new ArrayList<>();
    for (int i = 0; i < onionsByUser.size(); i++) {
      if (onionsByUser.get(i).getVisible().equals(Boolean.FALSE)) {
        continue;
      }
      result.add(GetOnionResponse.createGetOnionResponse(onionsByUser.get(i),
          onionCategories.get(i),
          Integer.parseInt(redisService.getValues(voiceNumberKey))
      ));
    }

    return result;
  }

  @Override
  public void deleteOnion(Long userId, Long onionId) {
    User user = userService.getUserById(userId);
    Onion onion = onionRepository.findById(onionId).orElseThrow();

    // 임시로 이미 만들어진 유저도 기본 양파 도감에 넣어두는 임시 코드
    String collectionsKey = "userId:" + userId + ":collections";
    redisService.setValueForSet(collectionsKey, "1");

    if (onion.getUser().getId().equals(user.getId())) {
      onion.deleteOnion();
      onionRepository.save(onion);
    }
  }


  @Override
  @Transactional
  public PromptResponseDto evolveOnion(Long onionId, String speech,
      ChatGPTResponse chatGPTResponse) {

    // 보이스에 저장
    Onion onion = onionRepository.findById(onionId).orElseThrow();
    Voice voice = Voice.createVoice(speech, onion);
    voiceRepository.save(voice);

    // 말할 수 있는 기회 기능
    String makeVoiceListKey = "userId:" + onion.getUser().getId() + ":voiceCreateTime";
    String original = String.valueOf(voice.getCreatedAt());
    String sliced = original.substring(0, 19); // 초단위까지만 저장
    redisService.setValueForList(makeVoiceListKey, sliced);
    if (redisService.getValuesForList(makeVoiceListKey).size() == 6) {
      redisService.deleteValueForList(makeVoiceListKey);
    }

    String makeVoiceNumberKey = "userId:" + onion.getUser().getId() + ":remainVoiceNumber";
    Integer voiceNumber = Integer.parseInt(redisService.getValues(makeVoiceNumberKey)) - 1;
    redisService.setValue(makeVoiceNumberKey, Integer.toString(voiceNumber));

    OnionCategory nowCategory = onion.getOnionCategory();
    Integer nowLevel = nowCategory.getLevel();
    Integer nowGroup = nowCategory.getGroup();
//    log.info(String.valueOf(nowLevel));
//    log.info(String.valueOf(nowGroup));

    if (nowCategory.getIsFinal()) {
      return new PromptResponseDto(false, -1);
    }

    if (chatGPTResponse.getPrimary() == null || chatGPTResponse.getPrimary().isEmpty()) {
      Random random = new Random();
      int randomCId = 34 + random.nextInt(2);

      onion.changeCategory(
          onionCategoryRepository.getReferenceById((long) randomCId));
      onionRepository.save(onion);
      return new PromptResponseDto(true, randomCId);
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
        return new PromptResponseDto(false, -1);
      }

      CategoryCount categoryCount = CategoryCount.createCategoryCount(onion, category.getGroup(),
          category.getId());
      categoryCountRepository.save(categoryCount);
    } else if (nowLevel == 2) {
      if (chatGPTResponse.getTertiary() == null) {
        return new PromptResponseDto(false, -1);
      }
      OnionCategory category = onionCategoryService.getOnionCategoryByCategoryName(
          chatGPTResponse.getTertiary());
      if (category.getGroup() != nowGroup) {
        return new PromptResponseDto(false, -1);
      }

      CategoryCount categoryCount = CategoryCount.createCategoryCount(onion, category.getGroup(),
          category.getId());
      categoryCountRepository.save(categoryCount);
    }

    //진화 알고리즘
    List<CategoryCount> categoryCounts = categoryCountService.getCategoryCountsByOnionId(onionId);
    boolean flag = false;
    long nowCategoryId = -1;

    if (nowLevel == 0) {
      int[] countGroup = new int[8];
      for (CategoryCount categoryCount : categoryCounts) {
        if (++countGroup[categoryCount.getGroupId()] == 2) {
          onion.changeCategory(
              onionCategoryRepository.getReferenceById(categoryCount.getCategoryId()));
          onionRepository.save(onion);
          nowCategoryId = categoryCount.getCategoryId();
          flag = true;
          String collectionsKey = "userId:" + onion.getUser().getId() + ":collections";
          redisService.setValueForSet(collectionsKey, Long.toString(nowCategoryId));
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
          nowCategoryId = categoryCount.getCategoryId();
          flag = true;
          String collectionsKey = "userId:" + onion.getUser().getId() + ":collections";
          redisService.setValueForSet(collectionsKey, Long.toString(nowCategoryId));
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
    return new PromptResponseDto(flag, nowCategoryId);
  }

  @Override
  public List<GetOnionBookResponse> getOnionBook(Long userId) {
    Set<String> haveOnionCategory = redisService.getValuesForSet(
        "userId:" + userId + ":collections");
    List<OnionCategory> allOnionCategory = onionCategoryService.getAllOnionCategory();
    List<GetOnionBookResponse> result = new ArrayList<>();

    for (OnionCategory onionCategory : allOnionCategory) {
      result.add(GetOnionBookResponse.createGetOnionBookResponse(
          onionCategory.getId(),
          haveOnionCategory.contains(String.valueOf(onionCategory.getId())),
          onionCategory.getCategoryName(),
          onionCategory.getOnionDetail()
      ));
    }

    return result;
  }
}
