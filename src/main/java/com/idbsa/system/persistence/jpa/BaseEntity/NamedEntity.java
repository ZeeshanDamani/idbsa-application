package com.idbsa.system.persistence.jpa.BaseEntity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class NamedEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

}
