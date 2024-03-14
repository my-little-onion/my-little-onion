package org.example.mylittleonion.entity;

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
public class OnionCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "level")
    private Integer level;

    @OneToMany(mappedBy = "onionCategory", cascade = CascadeType.ALL)
    private List<GroupRelation> groupRelations = new ArrayList<>();

    @OneToMany(mappedBy = "onionCategory", cascade = CascadeType.ALL)
    private List<Onion> onions = new ArrayList<>();
}
