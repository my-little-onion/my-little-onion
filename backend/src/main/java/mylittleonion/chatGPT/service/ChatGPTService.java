package mylittleonion.chatGPT.service;

import mylittleonion.api.voice.dto.ChatGPTResponse;
import org.springframework.stereotype.Service;

/**
 * ChatGPT 서비스 인터페이스
 */

@Service
public interface ChatGPTService {

  ChatGPTResponse prompt(String speechToString);


}