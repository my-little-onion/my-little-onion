package mylittleonion.common.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JWTProvider {

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
}
