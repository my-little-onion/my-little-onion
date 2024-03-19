package mylittleonion.chatGPT.service;

import mylittleonion.chatGPT.dto.CompletionRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ChatGPT 서비스 인터페이스
 */

@Service
public interface ChatGPTService {

    Map<String, Object> prompt(CompletionRequestDto completionRequestDto);


}