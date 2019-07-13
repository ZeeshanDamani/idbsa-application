package com.idbsa.system.persistence.jpa;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idbsa.system.persistence.jpa.BaseEntity.NamedEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "rank_badges")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankBadge extends NamedEntity {

    @Column(name = "code")
    private String code;

    @JoinColumn(name = "prerequisite",nullable = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private RankBadge preRequisite;

    @Column(name = "is_active")
    private boolean isActive;

    @JoinColumn(name = "section_id")
    @ManyToOne
    @JsonBackReference
    private Section section;
}
