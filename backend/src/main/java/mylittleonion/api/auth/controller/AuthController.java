package mylittleonion.api.auth.controller;




import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.auth.dto.KakaoUserInfoResponse;
import mylittleonion.api.auth.dto.LoginResponse;
import mylittleonion.api.auth.dto.TokenResponse;
import mylittleonion.api.auth.service.AuthService;
import mylittleonion.api.onion.dto.KakaoTokenResponse;
import mylittleonion.api.user.service.UserService;
import mylittleonion.api.user.service.UserServiceImpl;
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
  private final UserService userService;

  @GetMapping("/login/oauth2/code/kakao")
  ResponseEntity<ApiResponse<Void>> login(
      @RequestParam("code") String code,
      HttpServletResponse response) throws IOException {

    log.info(code);
    // 카카오 액세스 토큰 받아오기
    String accessToken = authService.getAccessToken(code);
    log.info(accessToken);

    KakaoUserInfoResponse kakaoUserInfoResponse = authService.getUserInfo(accessToken);
    TokenResponse tokenResponse = userService.login(kakaoUserInfoResponse);

    Cookie cookie = new Cookie("access-token", tokenResponse.getAccessToken());
    cookie.setHttpOnly(true);
    cookie.setMaxAge(60 * 60 * 24 * 30);
    cookie.setPath("/");
    response.addCookie(cookie);

    response.sendRedirect("https://j10a105.p.ssafy.io/choose");
//    response.sendRedirect("http://localhost:5173/choose");

    return ResponseEntity.ok(ApiResponse.success());
  }
}
