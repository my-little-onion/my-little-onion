package mylittleonion.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Voice extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onion_id")
    private Onion onion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}
