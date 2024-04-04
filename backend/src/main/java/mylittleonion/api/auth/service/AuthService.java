package mylittleonion.api.auth.service;

import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import mylittleonion.api.auth.dto.KakaoUserInfoResponse;

public interface AuthService {

  String getAccessToken(String code);

  KakaoUserInfoResponse getUserInfo(String accessToken);

  void saveRefreshToken(String accessToken, String refreshToken);

  boolean validateRefreshTokenInRedis(String refreshToken);
}
