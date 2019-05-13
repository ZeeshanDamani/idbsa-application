package com.idbsa.system.persistence.repository;

import com.idbsa.system.persistence.jpa.LeaderBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderBadgesRepository extends JpaRepository<LeaderBadge, Integer> {
}
