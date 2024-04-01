package mylittleonion.api.voice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetSttRequest {

  private Long onionId;

  private String stt;

  public static GetSttRequest createGetSttResponse(Long onionId, String stt) {
    return new GetSttRequest(onionId, stt);
  }
}

