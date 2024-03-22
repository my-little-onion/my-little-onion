package mylittleonion.api.onion.service;

import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.CreateOnionResponse;

public interface OnionService {

  CreateOnionResponse createOnion(Long userId, CreateOnionRequest createOnionRequest);
}
