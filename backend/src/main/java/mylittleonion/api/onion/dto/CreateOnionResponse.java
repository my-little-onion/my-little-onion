package mylittleonion.api.onion.dto;

import lombok.Data;

@Data
public class CreateOnionResponse {

  static CreateOnionResponse createOnionResponse() {
    return new CreateOnionResponse();
  }
}
