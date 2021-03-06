package com.idbsa.system.persistence.jpa;


import com.idbsa.system.persistence.jpa.BaseEntity.NamedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "leader_badges")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class LeaderBadge extends NamedEntity {

    @Column(name = "code")
    private String code;

//    @JoinColumn(name = "prerequisite")
//    @ManyToOne
//    @JsonBackReference
//    @Null
//    private LeaderBadge preRequisite;

    @Column(name = "is_active")
    private boolean isActive;
}
