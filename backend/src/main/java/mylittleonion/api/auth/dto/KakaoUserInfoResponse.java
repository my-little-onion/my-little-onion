package mylittleonion.api.auth.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class KakaoUserInfoResponse {
  private Integer id;
  private Properties properties;

  @Getter
  public class Properties {
    public String nickname;
  }

}
