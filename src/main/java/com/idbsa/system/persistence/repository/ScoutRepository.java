package com.idbsa.system.persistence.repository;

import com.idbsa.system.persistence.jpa.Scout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScoutRepository extends JpaRepository<Scout,Integer> {

    List<Scout> findByGroupIdAndSectionId(Integer groupId, Integer sectionId);

    List<Scout> findByGroupId(Integer groupId);

    Integer countBySectionIdAndGroupId(Integer sectionId, Integer groupId);

    Integer countBySectionId(Integer sectionId);

    @Query(value ="Select count(*) from scouts s JOIN group_unit gu ON s.group_id = gu.id JOIN jurisdiction j on gu.jurisdiction_id = j.id" +
            "   where s.section_id = ?1 AND j.id = ?2",
    nativeQuery = true)
    Integer countBySectionAndJurisdiction(Integer sectionId, Integer JurisdictionId);

    List<Scout> findByCnic(String cnic);
}
