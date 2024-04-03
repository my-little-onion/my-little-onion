package mylittleonion.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.auth.service.AuthService;
import mylittleonion.common.redis.RedisService;
import mylittleonion.common.util.JWTProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

  private final AuthService authService;
  private final JWTProvider jwtProvider;
  private final RedisService redisService;

  @Override
  protected void doFilterInternal(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain filterChain) throws ServletException, IOException {

    log.info("JWT필터~~~");
    String accessToken = getAccessToken(req);

    log.info("accessToken : {}", accessToken);

    //
    if (accessToken != null) {
      if (jwtProvider.isValidateToken(accessToken)) {
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else if(authService.validateRefreshTokenInRedis(accessToken)){
        accessToken = reIssueAccessToken(accessToken);
        log.info("재발급되니");
        Cookie cookie = new Cookie("access-token", accessToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");
        res.addCookie(cookie);
      }
      else {
        SecurityContextHolder.clearContext();
        res.setHeader("Authorization", null);

      }

    }
    filterChain.doFilter(req, res);
  }


  private String getAccessToken(HttpServletRequest req) {
    Cookie[] list = req.getCookies();
    if (list != null) {
      for (Cookie cookie : list) {
        log.info(cookie.toString());
        log.info(cookie.getName());
        log.info(cookie.getValue());
        if (cookie.getName().equals("access-token")) {
          String accessToken = cookie.getValue();
          return accessToken;
        }
      }
    }
    return null;
  }

  private String reIssueAccessToken(String accessToken) {
    // 토큰 재발급
    Long principal = jwtProvider.getClaims(accessToken).get("id",Long.class);
    String newAccessToken = jwtProvider.createAccessToken(principal);
    // securityContext에 저장
    Authentication authentication = jwtProvider.getAuthentication(newAccessToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return newAccessToken;
  }
}
