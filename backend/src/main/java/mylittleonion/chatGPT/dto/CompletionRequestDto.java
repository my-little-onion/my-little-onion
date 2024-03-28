package mylittleonion.chatGPT.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor // 기본 생성자의 접근 수준을 public으로 설정
public class CompletionRequestDto {

  @JsonProperty("max_tokens")
  private int maxTokens = 50;


  private final String model = "gpt-3.5-turbo-0125";
  private List<Message> messages;

  @Getter
  @Setter
  public static class Message {

    private String role;
    private String content;

    public Message(String role, String speechToString) {
      this.role = role;
      content = speechToString;
    }
  }

  @Builder
  public CompletionRequestDto(List<Message> messages) {
    this.messages = messages;
  }
}
