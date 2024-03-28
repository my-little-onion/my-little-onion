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

  public static GetOnionResponse createGetOnionResponse(Onion onion, OnionCategory onionCategory) {
    return new GetOnionResponse(onionCategory.getId(), onion.getId(), onion.getOnionName(),
        onionCategory.getLevel());
  }
}
