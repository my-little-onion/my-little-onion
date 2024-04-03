package mylittleonion.api.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.auth.dto.KakaoUserInfoResponse;
import mylittleonion.api.auth.dto.TokenResponse;
import mylittleonion.api.auth.service.AuthService;
import mylittleonion.api.user.repository.UserRepository;
import mylittleonion.common.entity.User;
import mylittleonion.common.redis.RedisService;
import mylittleonion.common.util.JWTProvider;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;
  private final JWTProvider jwtProvider;
  private final RedisService redisService;

  private final AuthService authService;

  @Override
  public User getUserById(Long userId) {
    return userRepository.findById(userId).orElseThrow();
  }

  @Override
  public void createUser(Long kakaoId, String nickName) {
    User user = User.builder()
        .user_nickname(nickName)
        .kakaoId(kakaoId)
        .build();
    userRepository.save(user);
    String collectionsKey = "userId:" + user.getId() + ":collections";
    redisService.setValueForSet(collectionsKey, "1");
  }

  @Override
  public TokenResponse login(KakaoUserInfoResponse kakaoUserInfoResponse) {

    // 첫 회원가입
    if (userRepository.findByKakaoId(kakaoUserInfoResponse.getId()).isEmpty()) {
      createUser(kakaoUserInfoResponse.getId(), kakaoUserInfoResponse.getProperties().nickname);
      log.info("최초 로그인 가입");
    }
    Long id = userRepository.findByKakaoId(kakaoUserInfoResponse.getId()).get().getId();

    // jwt 발급
    String accessToken = jwtProvider.createAccessToken(id);
    String refreshToken = jwtProvider.createRefreshToken(id);

    // redis에 refresh token 저장
    authService.saveRefreshToken(accessToken, refreshToken);

    return new TokenResponse(accessToken, refreshToken);
  }
}
