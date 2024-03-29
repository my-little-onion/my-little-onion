package mylittleonion.chatGPT.controller;

import mylittleonion.api.onion.service.OnionService;
import mylittleonion.api.voice.dto.ChatGPTResponse;
import mylittleonion.chatGPT.dto.PromptResponseDto;
import mylittleonion.chatGPT.service.ChatGPTService;
import mylittleonion.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ChatGPT API
 */
@RestController
@RequestMapping(value = "/chatgpt")
@CrossOrigin(origins = "*", maxAge = 3600) // CORS 설정
public class ChatGPTController {

  private final ChatGPTService chatGPTService;
  private final OnionService onionService;

  public ChatGPTController(ChatGPTService chatGPTService, OnionService onionService) {
    this.chatGPTService = chatGPTService;
    this.onionService = onionService;
  }

  @PostMapping("/prompt")
  public ResponseEntity<ApiResponse<PromptResponseDto>> selectPrompt(
      @RequestParam Long onionId,
      @RequestParam String speechToString) {
    ChatGPTResponse chatGPTResponse = chatGPTService.prompt(speechToString);

    return ResponseEntity.ok(
        ApiResponse.success(onionService.evolveOnion(onionId, speechToString, chatGPTResponse)));
  }

}