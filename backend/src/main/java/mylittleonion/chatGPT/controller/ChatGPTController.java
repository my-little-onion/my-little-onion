package mylittleonion.chatGPT.controller;

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
@RequestMapping(value = "/api/chatgpt")
@CrossOrigin(origins = "*", maxAge = 3600) // CORS 설정
public class ChatGPTController {

  private final ChatGPTService chatGPTService;

  public ChatGPTController(ChatGPTService chatGPTService) {
    this.chatGPTService = chatGPTService;
  }

  @PostMapping("/prompt")
  public ResponseEntity<ApiResponse<String>> selectPrompt(
      @RequestParam String speechToString) {
    String result = chatGPTService.prompt(speechToString);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

}