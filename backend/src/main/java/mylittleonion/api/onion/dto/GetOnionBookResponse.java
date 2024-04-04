package mylittleonion.api.onion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOnionBookResponse {

  private Long id;
  private Boolean have;
  private String onionCategoryName;
  private String onionCategoryDetail;

  public static GetOnionBookResponse createGetOnionBookResponse(Long id, Boolean have,
      String onionCategoryName, String onionCategoryDetail) {
    return new GetOnionBookResponse(id, have, onionCategoryName, onionCategoryDetail);
  }
}
