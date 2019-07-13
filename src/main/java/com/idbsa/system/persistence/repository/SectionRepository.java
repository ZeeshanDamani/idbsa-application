package com.idbsa.system.persistence.repository;


import com.idbsa.system.persistence.jpa.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section,Integer> {

}
