package mylittleonion.api.auth.controller;

import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.auth.dto.KakaoUserInfoResponse;
import mylittleonion.api.auth.dto.LoginResponse;
import mylittleonion.api.auth.service.AuthService;
import mylittleonion.api.onion.dto.KakaoTokenResponse;
import mylittleonion.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @GetMapping("/login")
  ResponseEntity<ApiResponse<LoginResponse>> login(
      @RequestParam("code") String code,
      HttpServletResponse response) {

    log.info(code);
    // 카카오 액세스 토큰 받아오기
    String accessToken = authService.getAccessToken(code);
    log.info(accessToken);

    // 이메일이랑 카카오 회원 정보 받기
    KakaoUserInfoResponse kakaoUserInfoResponse = authService.getUserInfo(accessToken);

    // 로그인 하기
//    authService.login(kakaoUserInfoResponse);
    return ResponseEntity.ok(ApiResponse.success(new LoginResponse(accessToken)));
  }
}
