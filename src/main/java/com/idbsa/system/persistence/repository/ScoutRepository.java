package com.idbsa.system.persistence.repository;

import com.idbsa.system.persistence.jpa.Scout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScoutRepository extends JpaRepository<Scout,Integer> {

    List<Scout> findByGroupIdAndSectionId(Integer groupId, Integer sectionId);

    List<Scout> findByGroupId(Integer groupId);

    Integer countBySectionIdAndGroupId(Integer sectionId, Integer groupId);
}
