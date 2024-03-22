package mylittleonion.chatGPT.service;

import org.springframework.stereotype.Service;

/**
 * ChatGPT 서비스 인터페이스
 */

@Service
public interface ChatGPTService {

  String prompt(String speechToString);


}