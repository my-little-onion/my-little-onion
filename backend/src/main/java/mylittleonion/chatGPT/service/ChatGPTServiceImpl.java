package mylittleonion.chatGPT.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.chatGPT.config.ChatGPTConfig;
import mylittleonion.chatGPT.dto.ChatGPTResponseDto;
import mylittleonion.chatGPT.dto.CompletionRequestDto;
import mylittleonion.chatGPT.dto.CompletionRequestDto.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * ChatGPT Service 구현체
 */
@Slf4j
@Service
public class ChatGPTServiceImpl implements ChatGPTService {

  private final ChatGPTConfig chatGPTConfig;

  public ChatGPTServiceImpl(ChatGPTConfig chatGPTConfig) {
    this.chatGPTConfig = chatGPTConfig;
  }

//    @Value("${openai.model}")
//    private String model;


  /**
   * ChatGTP 프롬프트 검색
   *
   * @param speechToString
   * @return
   */
  @Override
  public String prompt(String speechToString) {
    List<Message> messages = new ArrayList<>();
    messages.add(new CompletionRequestDto.Message(speechToString)); // s를 사용하여 Message 객체 생성

    CompletionRequestDto completionRequestDto = CompletionRequestDto.builder()
        .messages(messages)
        .build(); // CompletionRequestDto 객체 생성
    String result = "";

    HttpHeaders headers = chatGPTConfig.httpHeaders();
    ObjectMapper om = new ObjectMapper();
    String requestBody = "";

    try {
      requestBody = om.writeValueAsString(completionRequestDto);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
    ResponseEntity<String> response = chatGPTConfig.restTemplate()
        .exchange("https://api.openai.com/v1/chat/completions", HttpMethod.POST, requestEntity,
            String.class);

    try {
      ChatGPTResponseDto gptResponse = om.readValue(response.getBody(), ChatGPTResponseDto.class);
      result = gptResponse.getChoices().get(0).getMessage().getContent();
      log.info(result);
//      for (ChatGPTResponseDto.Choice choice : gptResponse.getChoices()) {
//          result = choice.getText();
//          break; // 첫 번째로 발견한 '행복' 또는 '불행'을 결과에 추가하고 반복 종료
//        }
//      }
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
}