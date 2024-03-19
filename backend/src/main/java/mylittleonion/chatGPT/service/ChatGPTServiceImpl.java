package mylittleonion.chatGPT.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mylittleonion.chatGPT.config.ChatGPTConfig;
import mylittleonion.chatGPT.dto.CompletionRequestDto;
import mylittleonion.chatGPT.dto.ChatGPTResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
     * @param completionRequestDto
     * @return
     */
    @Override
    public Map<String, Object> prompt(CompletionRequestDto completionRequestDto) {
        Map<String, Object> result = new HashMap<>();

        // 토큰 정보가 포함된 Header를 가져옴
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        String requestBody = "";
        ObjectMapper om = new ObjectMapper();

        completionRequestDto = completionRequestDto.builder()
                .prompt(completionRequestDto.getPrompt())
                .maxTokens(completionRequestDto.getMaxTokens())
                .model(completionRequestDto.getModel())
                .messages(completionRequestDto.getMessages())
                .build();

        try {
            // Object -> String 직렬화를 구성
            requestBody = om.writeValueAsString(completionRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 통신을 위한 RestTemplate을 구성
        HttpEntity requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = chatGPTConfig.restTemplate()
                .exchange(
                        "https://api.openai.com/v1/chat/completions", // URL 변경
                        HttpMethod.POST,
                        requestEntity,
                        String.class
                );

        try {
            ChatGPTResponseDto gptResponse = om.readValue(response.getBody(), ChatGPTResponseDto.class);
            // GPTResponse 객체를 Map<String, Object>로 변환하거나, 필요한 데이터만 추출하여 Map에 저장
            result.put("id", gptResponse.getId());
            result.put("object", gptResponse.getObject());
            result.put("model", gptResponse.getModel());
            result.put("choices", gptResponse.getChoices());
            result.put("usage", gptResponse.getUsage());

//            // String -> HashMap 역직렬화를 구성합니다.
//            result = om.readValue(response.getBody(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}