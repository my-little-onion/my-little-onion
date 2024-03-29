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
public class Voice extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "contents")
  private String contents;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "onion_id")
  private Onion onion;


  public static Voice createVoice(String speech, Onion onion) {

    return Voice.builder()
        .contents(speech)
        .onion(onion)
        .build();
  }
}
