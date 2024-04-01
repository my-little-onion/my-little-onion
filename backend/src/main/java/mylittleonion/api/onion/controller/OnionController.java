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
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(onionService.getOnion(1L))
    );
  }

  @PostMapping("/onion")
  ResponseEntity<ApiResponse<String>> makeOnion(
      @RequestBody CreateOnionRequest createOnionRequest
  ) {
    onionService.createOnion(1L, createOnionRequest);
    return ResponseEntity.ok(
        ApiResponse.success("생성되었습니다.")
    );
  }

  @DeleteMapping("/onion")
  ResponseEntity<ApiResponse<List<GetVoiceResponse>>> deleteOnion(
      @RequestParam Long onionId
  ) {
    List<GetVoiceResponse> voices = voiceService.getVoices(onionId);
    onionService.deleteOnion(1L, onionId);
    return ResponseEntity.ok(
        ApiResponse.success(voices)
    );
  }

  @GetMapping("/onion/book")
  ResponseEntity<ApiResponse<List<GetOnionBookResponse>>> getOnionBook(
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(onionService.getOnionBook(1L))
    );
  }
}
