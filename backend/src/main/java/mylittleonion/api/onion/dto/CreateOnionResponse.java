package mylittleonion.api.onion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOnionResponse {

  static CreateOnionResponse createOnionResponse() {
    return new CreateOnionResponse();
  }
}
