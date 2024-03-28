package mylittleonion.api.auth.service;

import lombok.RequiredArgsConstructor;
import mylittleonion.api.auth.dto.KakaoUserInfoResponse;
import mylittleonion.api.onion.dto.KakaoTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  //yml 파일에서 받아오기
  @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
  String redirectURI;
  @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
  String clientSecret;
  @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
  String clientId;

  @Override
  public String getAccessToken(String code) {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("code", code);
    formData.add("grant_type", "authorization_code");
    formData.add("redirect_uri", redirectURI);
    formData.add("client_id", clientId);
    // 필수는 아님
    formData.add("client_secret", clientSecret);

    return WebClient.create()
        .post()
        .uri("https://kauth.kakao.com/oauth/token")
        .header("Content-type", "application/x--form-urlencoded;charset=utf-8")
        .body(BodyInserters.fromFormData(formData))
        .retrieve()
        .bodyToMono(KakaoTokenResponse.class)
        .block().getAccess_token();
  }

  @Override
  public KakaoUserInfoResponse getUserInfo(String accessToken) {
    KakaoUserInfoResponse kakaoUserInfoResponse = WebClient.create()
        .post()
        .uri("https://kapi.kakao.com/v2/user/me")
        .header("Authorization", "Bearer " + accessToken)
        .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
        .retrieve()
        .bodyToMono(KakaoUserInfoResponse.class)
        .block();
    return kakaoUserInfoResponse;
  }



  // 디비랑
  // 레디스
}
