package mylittleonion.api.onion.service;

import java.util.List;
import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.GetOnionBookResponse;
import mylittleonion.api.onion.dto.GetOnionResponse;

public interface OnionService {

  void createOnion(Long userId, CreateOnionRequest createOnionRequest);

  List<GetOnionResponse> getOnion(Long userId);

  void deleteOnion(Long userId, Long onionId);

  List<GetOnionBookResponse> getOnionBook(Long userId);
}
