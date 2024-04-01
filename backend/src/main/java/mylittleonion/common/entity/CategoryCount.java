package mylittleonion.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CategoryCount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ccid")
  private int ccid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "onion_id")
  private Onion onion;

  @Column(name = "group_id")
  private int groupId;

  @Column(name = "category_id")
  private long categoryId;

  public static void InitializeCategoryCount() {

  }

  public static CategoryCount createCategoryCount(Onion onion, int groupId, long categoryId) {

    return CategoryCount.builder()
        .onion(onion)
        .groupId(groupId)
        .categoryId(categoryId)
        .build();
  }
}
