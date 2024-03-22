package mylittleonion.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_nickname")
  private String user_nickname;

  @Column(name = "kakao_id")
  private Integer kakaoId;

  @Column(name = "representative_Onion")
  private Integer representativeOnion;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Onion> onions = new ArrayList<>();
}
