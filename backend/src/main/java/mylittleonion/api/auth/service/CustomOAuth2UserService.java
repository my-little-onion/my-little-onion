package mylittleonion.api.auth.service;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.user.repository.UserRepository;
import mylittleonion.common.entity.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  private final UserRepository userRepository;
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    log.info("getAttributes: {}", oAuth2User.getAttributes());

    Map<String, Object> properties = oAuth2User.getAttribute("properties");
    Map<String, Object> account = oAuth2User.getAttribute("kakao_account");

    String provider = userRequest.getClientRegistration().getRegistrationId();
    log.info(provider);

    String providerId = oAuth2User.getAttribute("id").toString();
    log.info(providerId);
    String loginId = provider + "_" + providerId;
    log.info("loginId : " + loginId);
    log.info("account : "+account.toString());

    log.info("nickname : "+(String)properties.get("nickname"));

    Optional<Optional<User>> optionalUser = Optional.ofNullable(userRepository.findByKakaoId(123L));
    User user = null;
    if(optionalUser.isEmpty()) {
      user = User.builder()
          .user_nickname("asdas")
          .build();
      userRepository.save(user);
    } else {
      System.out.println("error");

    }

    return new PrincipalDetails(user, oAuth2User.getAttributes());
  }
}