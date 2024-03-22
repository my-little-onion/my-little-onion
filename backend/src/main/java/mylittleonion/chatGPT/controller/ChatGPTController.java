package mylittleonion.chatGPT.controller;

import mylittleonion.chatGPT.dto.CompletionRequestDto;
import mylittleonion.chatGPT.service.ChatGPTService;
import mylittleonion.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ChatGPT API
 */
@RestController
@RequestMapping(value = "/api/chatgpt")
@CrossOrigin(origins = "http://example.com", maxAge = 3600) // CORS 설정
public class ChatGPTController {

  private final ChatGPTService chatGPTService;

  public ChatGPTController(ChatGPTService chatGPTService) {
    this.chatGPTService = chatGPTService;
  }

  @PostMapping("/prompt")
  public ResponseEntity<ApiResponse<Map<String, Object>>> selectPrompt(
      @RequestBody CompletionRequestDto completionRequestDto) {
    Map<String, Object> result = chatGPTService.prompt(completionRequestDto);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

}