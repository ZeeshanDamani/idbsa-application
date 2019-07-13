package com.idbsa.system.persistence.jpa;


import com.idbsa.system.persistence.jpa.BaseEntity.NamedEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "leader_rank")
public class Rank extends NamedEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "is_active")
    private boolean isActive;

}
