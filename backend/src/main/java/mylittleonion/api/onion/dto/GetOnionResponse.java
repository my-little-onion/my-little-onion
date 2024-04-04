package mylittleonion.api.onion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mylittleonion.common.entity.Onion;
import mylittleonion.common.entity.OnionCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOnionResponse {

  private Long onionCategoryId;

  private Long onionId;

  private String onionName;

  private Integer onionLevel;

  private Boolean isFinal;

  private Integer voiceNumber;

  public static GetOnionResponse createGetOnionResponse(Onion onion, OnionCategory onionCategory,
      Integer voiceNumber) {
    return new GetOnionResponse(onionCategory.getId(), onion.getId(), onion.getOnionName(),
        onionCategory.getLevel(), onionCategory.getIsFinal(), voiceNumber);
  }
}
