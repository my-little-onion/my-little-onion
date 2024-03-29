package mylittleonion.api.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mylittleonion.api.user.repository.UserRepository;
import mylittleonion.common.entity.User;
import mylittleonion.common.util.JWTProvider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;
  private final JWTProvider jwtProvider;
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

    //// redis에 refresh token 저장
    //     authService.saveRefreshToken(provider, email, RefreshToken);
    //     userService.setAttendance(email);

    // 인증 정보 저장
    Authentication newAuthentication = jwtProvider.getAuthentication(accessToken);
    SecurityContextHolder.getContext().setAuthentication(newAuthentication);

    return new TokenResponse(accessToken, refreshToken);
  }
}
