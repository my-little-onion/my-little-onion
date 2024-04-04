package mylittleonion.api.onion.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.GetOnionBookResponse;
import mylittleonion.api.onion.dto.GetOnionResponse;
import mylittleonion.api.onion.service.OnionService;
import mylittleonion.api.voice.dto.GetVoiceResponse;
import mylittleonion.api.voice.service.VoiceService;
import mylittleonion.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OnionController {

  private final OnionService onionService;
  private final VoiceService voiceService;

  @GetMapping("/onion")
  ResponseEntity<ApiResponse<List<GetOnionResponse>>> getOnion(
      Authentication authentication
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(onionService.getOnion((Long) authentication.getPrincipal()))
    );
  }

  @PostMapping("/onion")
  ResponseEntity<ApiResponse<String>> makeOnion(
      Authentication authentication,
      @RequestBody CreateOnionRequest createOnionRequest
  ) {
    onionService.createOnion((Long) authentication.getPrincipal(), createOnionRequest);
    return ResponseEntity.ok(
        ApiResponse.success("생성되었습니다.")
    );
  }

  @DeleteMapping("/onion")
  ResponseEntity<ApiResponse<List<GetVoiceResponse>>> deleteOnion(Authentication authentication,

      @RequestParam Long onionId
  ) {
    List<GetVoiceResponse> voices = voiceService.getVoices(onionId);
    onionService.deleteOnion((Long) authentication.getPrincipal(), onionId);
    return ResponseEntity.ok(
        ApiResponse.success(voices)
    );
  }

  @GetMapping("/onion/book")
  ResponseEntity<ApiResponse<List<GetOnionBookResponse>>> getOnionBook(
      Authentication authentication
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(onionService.getOnionBook((Long) authentication.getPrincipal()))
    );
  }
}
