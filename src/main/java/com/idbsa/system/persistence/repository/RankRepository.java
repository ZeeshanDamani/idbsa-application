package com.idbsa.system.persistence.repository;


import com.idbsa.system.persistence.jpa.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Integer> {

//    List<Rank> findBySectionId(Integer sectionId);
}
