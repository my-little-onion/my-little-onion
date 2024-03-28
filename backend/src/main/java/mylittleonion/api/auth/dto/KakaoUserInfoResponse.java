package mylittleonion.api.auth.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class KakaoUserInfoResponse {
  private Long id;
  private Properties properties;

  @Getter
  public class Properties {
    public String nickname;
  }

}
