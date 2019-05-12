package com.idbsa.system.persistence.jpa;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idbsa.system.persistence.jpa.BaseEntity.NamedEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Setter
@Getter
@Entity(name = "group_unit")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Group extends NamedEntity {


    @JoinColumn(name = "district_id")
    @ManyToOne
    private District district;

    @Column(name="code")
    private String code;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name = "jamatkhana")
    private String jamatKhana;

    @JoinColumn(name="jurisdiction_id")
    @ManyToOne
    @JsonBackReference
    Jurisdiction jurisdiction;

}
