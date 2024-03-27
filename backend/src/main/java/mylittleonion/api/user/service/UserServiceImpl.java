package mylittleonion.api.user.service;

import com.nimbusds.oauth2.sdk.TokenResponse;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mylittleonion.api.auth.dto.KakaoUserInfoResponse;
import mylittleonion.api.auth.dto.LoginResponse;
import mylittleonion.api.user.repository.UserRepository;
import mylittleonion.common.entity.User;
import mylittleonion.common.util.JWTProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JWTProvider jwtProvider;
  @Override
  public User getUserById(Long userId) {
    return null;
  }

  @Override
  public void createUser(Integer kakaoId, String nickName) {
    User user = User.builder()
        .user_nickname(nickName)
        .kakaoId(kakaoId)
        .build();
    userRepository.save(user);
  }

  @Override
  public LoginResponse login(KakaoUserInfoResponse kakaoUserInfoResponse) {
    //가입 되있는 회원인지 확인

    Optional<User> user = userRepository.findByKakaoId(kakaoUserInfoResponse.getId());

    // 첫 회원가입
    if (!user.isPresent()) {
      createUser(kakaoUserInfoResponse.getId(), kakaoUserInfoResponse.getProperties().nickname);
    }

    // 토큰을 받아요
    String accessToken = ;

  }
}
