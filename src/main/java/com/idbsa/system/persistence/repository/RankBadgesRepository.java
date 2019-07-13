package com.idbsa.system.persistence.repository;

import com.idbsa.system.persistence.jpa.RankBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankBadgesRepository extends JpaRepository<RankBadge, Integer> {

    List<RankBadge> findBySectionId(Integer sectionId);
}
