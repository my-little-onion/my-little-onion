package mylittleonion.chatGPT.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromptResponseDto {

  public Boolean canEvolve;

  public long categoryId;

}
