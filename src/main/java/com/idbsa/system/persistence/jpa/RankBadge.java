package com.idbsa.system.persistence.jpa;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idbsa.system.persistence.jpa.BaseEntity.NamedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "rank_badges")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class RankBadge extends NamedEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "prerequisite")
    private RankBadge preRequisite;

    @Column(name = "is_active")
    private boolean isActive;

    @JoinColumn(name = "section_id")
    @ManyToOne
    @JsonBackReference
    private Section section;

}
