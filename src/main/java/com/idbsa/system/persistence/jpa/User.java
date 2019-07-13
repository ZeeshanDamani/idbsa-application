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
@Entity(name = "section")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class User extends NamedEntity {

    @Column(name ="email")
    private String email;

    @Column(name ="password")
    private String password;

    @Column(name ="group_id")
    private int group;

    @Column(name ="role_od")
    private int role;


}
