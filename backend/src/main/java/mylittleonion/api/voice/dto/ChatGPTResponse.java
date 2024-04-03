package mylittleonion.api.voice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatGPTResponse {

  private String primary;
  private String secondary;
  private String tertiary;
}
