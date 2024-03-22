package mylittleonion.common.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OnionCategory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "level")
  private Integer level;

  @Column(name = "onion_detail")
  private String onionDetail;

  @OneToMany(mappedBy = "onionCategory", cascade = CascadeType.ALL)
  private List<GroupRelation> groupRelations = new ArrayList<>();

  @OneToMany(mappedBy = "onionCategory", cascade = CascadeType.ALL)
  private List<Onion> onions = new ArrayList<>();
}
