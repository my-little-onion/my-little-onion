package mylittleonion.chatGPT.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor // 기본 생성자의 접근 수준을 public으로 설정
public class CompletionRequestDto {

    @JsonProperty("max_tokens")
    private int maxTokens;

    private String prompt;

    private String model;
    private List<Message> messages;

    public static class Message {
        private String role;
        private String content;
    }

    @Builder
    public CompletionRequestDto(String prompt, int maxTokens, String model, List<Message> messages) {
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.model = model;
        this.messages = messages;
    }
}
