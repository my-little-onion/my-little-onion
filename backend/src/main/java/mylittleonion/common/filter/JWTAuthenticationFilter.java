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
import mylittleonion.common.util.JWTProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

  private final AuthService authService;
  private final JWTProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain filterChain) throws ServletException, IOException {

    String accessToken = getAccessToken(req);
    String refreshToken = null;
    Cookie[] list = req.getCookies();
    if (list != null) {
      for (Cookie cookie : list) {
        if (cookie.getName().equals("refresh-token")) {
          refreshToken = cookie.getValue();
        }
      }
    }
    log.info("accessToken : {}", accessToken);

    //
    if(accessToken != null){
      if(jwtProvider.isValidateToken(accessToken)){
//        Authentication authentication = jwtProvider.getAuthentication(accessToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("인증 정보 저장");
        res.setHeader("Authorization", "Bearer " + accessToken);
      }
//      else if(authService.validateRefreshTokenInRedis(refreshToken)){
//        accessToken = reIssueAccessToken(accessToken);
//        res.setHeader("Authorization", "Bearer " + accessToken);
//        log.info("accessToken 재발급");
//      }
      else{
        SecurityContextHolder.clearContext();
        res.setHeader("Authorization", null);

      }

    }
    filterChain.doFilter(req, res);
  }


  private String getAccessToken(HttpServletRequest req) {
    String token = req.getHeader("Authorization");
    log.info("accessToken : {}", token);
    if (token != null && token.startsWith("Bearer ")) {
      return token.substring(7);
    }
    return null;
  }

  private String reIssueAccessToken(String refreshToken) {
    // 토큰 재발급
    Long id = jwtProvider.getId(refreshToken);
    String newAccessToken = jwtProvider.createAccessToken(id);

    // securityContext에 저장
//    Authentication authentication = jwtProvider.getAuthentication(newAccessToken);
//    SecurityContextHolder.getContext().setAuthentication(authentication);

    return newAccessToken;


  }
}
