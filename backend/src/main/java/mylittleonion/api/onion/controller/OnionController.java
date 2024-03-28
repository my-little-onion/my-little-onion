package mylittleonion.api.onion.controller;

import lombok.RequiredArgsConstructor;
import mylittleonion.api.onion.dto.CreateOnionRequest;
import mylittleonion.api.onion.dto.CreateOnionResponse;
import mylittleonion.api.onion.service.OnionService;
import mylittleonion.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OnionController {

  private final OnionService onionService;

  @PostMapping("/onion")
  ResponseEntity<ApiResponse<CreateOnionResponse>> makeOnion(
      @RequestBody CreateOnionRequest createOnionRequest
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(onionService.createOnion(1L, createOnionRequest))
    );
  }
}
