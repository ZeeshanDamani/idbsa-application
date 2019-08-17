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
@Entity(name = "section")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Section extends NamedEntity {

    @Column(name ="minimum_age")
    private int minimumAge;

    @Column(name ="maximum_age")
    private int MaximumAge;

    @Column(name ="scouts_per_unit")
    private int scoutsPerunit;

    @Column(name ="leaders_per_unit")
    private int leadersPerUnit;

    @Column(name ="patrol_size")
    private int patrolSize;

    @JoinColumn(name = "basic_rank_badge_id")
    @ManyToOne
    @JsonBackReference
    private RankBadge basicRankBadge;

}
