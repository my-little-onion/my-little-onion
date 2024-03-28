package mylittleonion.api.onion.service;

import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.CreateOnionResponse;
import mylittleonion.api.onion.dto.GetOnionResponse;

public interface OnionService {

  CreateOnionResponse createOnion(Long userId, CreateOnionRequest createOnionRequest);

  GetOnionResponse getOnion(Long userId, Integer onionNumber);

  void deleteOnion(Long userId, Long onionId);
}
