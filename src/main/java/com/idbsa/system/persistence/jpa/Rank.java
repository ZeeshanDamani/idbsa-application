package com.idbsa.system.persistence.jpa;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idbsa.system.persistence.jpa.BaseEntity.NamedEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "scouts_rank")
public class Rank extends NamedEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "is_leader_rank")
    private boolean leaderRank;

    @Column(name = "is_active")
    private boolean active;

    @JoinColumn(name = "section_id")
    @ManyToOne
    @JsonBackReference
    private Section section;



}
