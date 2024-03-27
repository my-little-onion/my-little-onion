package mylittleonion.chatGPT.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatGPTResponseDto {

  private String id;
  private String object;
  private Long created;
  private String model;
  private List<Choice> choices;
  private Usage usage;
  @JsonProperty("system_fingerprint")
  private String systemFingerprint;

  // GPTResponse의 Getters and Setters

  @Getter
  public static class Choice {

    private String text;
    private int index;
    private Object logprobs; // 실제 사용 시, 필요에 따라 적절한 타입으로 변경
    @JsonProperty("finish_reason")
    private String finishReason;
    private Message message; // Message 객체를 포함하는 새로운 필드 추가

    @Getter
    public static class Message {

      private String role;
      private String content;
    }
  }

  public static class Usage {

    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("completion_tokens")
    private int completionTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;

  }

}