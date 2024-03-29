package mylittleonion.api.voice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSttRequest {

  private Long onionId;

  private String stt;

  public static GetSttRequest createGetSttResponse(Long onionId, String stt) {
    return new GetSttRequest(onionId, stt);
  }
}

