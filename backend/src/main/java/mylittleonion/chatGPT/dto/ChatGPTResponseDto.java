package mylittleonion.chatGPT.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatGPTResponseDto {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    // GPTResponse의 Getters and Setters

    public static class Choice {
        private String text;
        private int index;
        private Object logprobs; // 실제 사용 시, 필요에 따라 적절한 타입으로 변경
        private String finishReason;

    }

    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;

    }

}