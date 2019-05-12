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
@Entity(name = "jurisdiction")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Jurisdiction extends NamedEntity {

    @Column(name = "code")
    String code;

//    @OneToMany(targetEntity = Group.class, fetch = FetchType.LAZY, mappedBy = "jurisdiction")
//    @JsonManagedReference
//    private Collection<Group> groups = new ArrayList<Group>();
}
