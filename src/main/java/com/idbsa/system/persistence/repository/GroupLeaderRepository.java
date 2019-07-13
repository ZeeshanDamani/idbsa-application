package com.idbsa.system.persistence.repository;


import com.idbsa.system.persistence.jpa.GroupLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupLeaderRepository extends JpaRepository<GroupLeader, Integer> {

    List<GroupLeader> findByGroupId(Integer groupId);

    Integer countByGroupId(Integer groupId);
}
