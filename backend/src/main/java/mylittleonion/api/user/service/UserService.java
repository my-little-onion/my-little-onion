package mylittleonion.api.user.service;

import mylittleonion.api.auth.dto.KakaoUserInfoResponse;
import mylittleonion.api.auth.dto.LoginResponse;
import mylittleonion.common.entity.User;

public interface UserService {

  User getUserById(Long userId);

  void createUser(Long kakaoId, String nickName);

  LoginResponse login(KakaoUserInfoResponse kakaoUserInfoResponse);
}
