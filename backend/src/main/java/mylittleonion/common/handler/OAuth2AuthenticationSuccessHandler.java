package mylittleonion.common.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.common.util.JWTProvider;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final JWTProvider jwtProvider;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {

		log.info("시작");
		// 확인
		Long id = (Long) authentication.getPrincipal();

		// jwt 발급
		String accessToken = jwtProvider.createAccessToken(id);
		String refreshToken = jwtProvider.createRefreshToken(id);


		// 인증 정보 저장
		Authentication newAuthentication = jwtProvider.getAuthentication(accessToken);
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);

		//쿠키, 헤더 설정
		Cookie cookie = new Cookie("refresh-token", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookie.setPath("/");
		response.addCookie(cookie);
		response.setHeader("Authorization", "Bearer " + accessToken);

		log.info("oauth 로그인 성공");


	}
}

