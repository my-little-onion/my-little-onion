package mylittleonion.common.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Onion extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "onion_name")
  private String onionName;

  @Column(name = "visible")
  private Boolean visible;

  @Column(name = "last_voice")
  private LocalDate lastVoice;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "onion_category_id")
  private OnionCategory onionCategory;

  @OneToMany(mappedBy = "onion", cascade = CascadeType.ALL)
  private List<Voice> voices = new ArrayList<>();

  public static Onion makeNewOnion(String onionName, User user, OnionCategory onionCategory) {
    return Onion.builder()
        .onionName(onionName)
        .visible(Boolean.TRUE)
        .lastVoice(null)
        .user(user)
        .onionCategory(onionCategory)
        .build();
  }

  public void deleteOnion() {
    this.visible = Boolean.FALSE;
  }
}
