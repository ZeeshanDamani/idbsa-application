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
@Entity(name = "district")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class District extends NamedEntity {

    @Column(name = "code")
    String code;

    @Column(name = "is_active")
    boolean isActive;
}
