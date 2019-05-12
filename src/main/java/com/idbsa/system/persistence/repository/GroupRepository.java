package com.idbsa.system.persistence.repository;

import com.idbsa.system.persistence.jpa.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findByJurisdictionId(Integer jurisdictionId);
}
