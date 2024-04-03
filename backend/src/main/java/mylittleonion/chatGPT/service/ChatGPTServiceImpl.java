package mylittleonion.chatGPT.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.api.voice.dto.ChatGPTResponse;
import mylittleonion.chatGPT.config.ChatGPTConfig;
import mylittleonion.chatGPT.dto.ChatGPTResponseDto;
import mylittleonion.chatGPT.dto.ChatGPTResponseParser;
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

  private final String delimiter = "####";
  private final String system_message = String.format(
      "You will be provided with someone's feeling queries. \n" +
          "The customer service query will be delimited with \n" +
          "%s characters.\n" +
          "Classify each query into a primary category \n" +
          "and a secondary category\n" +
          "and a tertiary category.\n" +
          "Provide your output in json format with the \n" +
          "keys: primary and secondary and tertiary.\n\n" +
          "Primary categories: 불량배, 불만, 학생, 만화, 운동, 부정적, 긍정적 \n\n"
          +
          "불량배 secondary categories:\n" +
          "깡패\n" +
          "도둑\n" +
          "불만 secondary categories:\n" +
          "조커\n" +
          "정치인\n\n" +
          "학생 secondary categories:\n" +
          "개발자\n" +
          "아이돌\n" +
          "엄마\n" +
          "아빠\n" +
          "회사원\n\n" +
          "만화 secondary categories:\n" +
          "매니아\n\n" +
          "운동 secondary categories:\n" +
          "메달리스트\n" +
          "헬스트레이너\n\n" +
          "부정적 secondary categories:\n" +
          "화난\n" +
          "우울\n\n" +
          "긍정적 secondary categories:\n" +
          "천사\n" +
          "광대\n\n" +
          "깡패 tertiary categories:\n" +
          "마피아\n\n" +
          "정치인 tertiary categories:\n" +
          "대통령\n\n" +
          "아이돌 tertiary categories:\n" +
          "공주\n\n" +
          "엄마 tertiary categories:\n" +
          "할머니\n\n" +
          "아빠 tertiary categories:\n" +
          "할아버지\n\n" +
          "오타쿠 tertiary categories:\n" +
          "마법사\n\n" +
          "화난 tertiary categories:\n" +
          "악마\n\n" +
          "우울 tertiary categories:\n" +
          "환자\n\n" +
          "천사 tertiary categories:\n" +
          "가브리엘\n\n",
      delimiter);


  private final ChatGPTConfig chatGPTConfig;

  public ChatGPTServiceImpl(ChatGPTConfig chatGPTConfig) {
    this.chatGPTConfig = chatGPTConfig;
  }


  /**
   * ChatGTP 프롬프트 검색
   *
   * @param speechToString
   * @return
   */
  @Override
  public ChatGPTResponse prompt(String speechToString) {
    // String speechToString이 아니라 GetSttRequest로 받아야하나?
    List<Message> messages = new ArrayList<>();
    messages.add(new CompletionRequestDto.Message("system", system_message));
    messages.add(new CompletionRequestDto.Message("user", speechToString));

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

    ChatGPTResponse chatGPTResponse;

    try {
      ChatGPTResponseDto gptResponse = om.readValue(response.getBody(), ChatGPTResponseDto.class);
      result = gptResponse.getChoices().get(0).getMessage().getContent();
      // result -> chatGPTResponse
      log.info(result);

      chatGPTResponse = ChatGPTResponseParser.parse(result);

    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return chatGPTResponse;
  }
}