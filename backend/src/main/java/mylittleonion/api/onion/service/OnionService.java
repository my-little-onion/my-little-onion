package mylittleonion.api.onion.service;

import java.util.List;
import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.CreateOnionResponse;
import mylittleonion.api.onion.dto.GetOnionResponse;
import mylittleonion.api.voice.dto.ChatGPTResponse;
import mylittleonion.chatGPT.dto.PromptResponseDto;

public interface OnionService {

  CreateOnionResponse createOnion(Long userId, CreateOnionRequest createOnionRequest);

  List<GetOnionResponse> getOnion(Long userId);

  void deleteOnion(Long userId, Long onionId);

  PromptResponseDto evolveOnion(Long onionId, String speech, ChatGPTResponse chatGPTResponse);
}
