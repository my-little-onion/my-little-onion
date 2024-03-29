package mylittleonion.common.util;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.auth.service.CustomOAuth2UserService;
import mylittleonion.common.dto.ErrorBase;
import mylittleonion.common.exception.InvalidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JWTProvider {

  private CustomOAuth2UserService customOAuth2UserService;

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.access-token.expiration}")
  private Long accessTokenExpirationTime;

  @Value("${jwt.refresh-token.expiration}")
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


 public Authentication getAuthentication(String token) {
   Long id = getId(token);
   Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
   return new UsernamePasswordAuthenticationToken(id, null, authorities);
 }
}