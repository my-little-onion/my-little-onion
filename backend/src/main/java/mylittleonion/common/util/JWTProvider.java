package mylittleonion.common.util;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.auth.service.CustomOAuth2UserService;
import mylittleonion.common.dto.ErrorBase;
import mylittleonion.common.exception.InvalidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JWTProvider {

  private CustomOAuth2UserService customOAuth2UserService;

  @Value("${jwt.secretKey}")
  private String secretKey;

  @Value("${jwt.accessToken.expirationTime}")
  private Long accessTokenExpirationTime;

  @Value("${jwt.refreshToken.expirationTime}")
  private Long refreshTokenExpirationTime;

  public String createToken(String subject, Long id, Long expirationTIme) {
    long now = System.currentTimeMillis();

    return Jwts.builder()
          .setHeaderParam("typ", "JWT")
          .setHeaderParam("alg", "HS512")
          .setSubject(subject)
          .claim("id", id)
          .setExpiration(new Date(now + expirationTIme))
          .signWith(SignatureAlgorithm.HS512, secretKey)
          .compact();
  }

  public String createAccessToken(Long id) {
    return createToken("access", id, accessTokenExpirationTime);
  }

  public String createRefreshToken(Long id) {
    return createToken("refresh", id, refreshTokenExpirationTime);
  }


  public long getId(String token) {
    try {
      return Jwts.parser()
          .setSigningKey(secretKey)
          .parseClaimsJws(token)
          .getBody()
          .get("id", Long.class);
    } catch (ExpiredJwtException e) {
      throw new InvalidException(ErrorBase.E400_INVALID_TOKEN);
    }
  }

  public boolean isValidateToken(String token) {
    try {
      Jwts.parser()
          .setSigningKey(secretKey)
          .parseClaimsJws(token)
          .getBody();
      return true;
    } catch (Exception e) {
      return false;
    }
  }


//  public Authentication getAuthentication(String token) {
//    Long id = getId(token);
//    return new OAuth2AuthenticationToken(oAuth2User, oAuth2User.getAuthorities(), "asd");
//  }
}
