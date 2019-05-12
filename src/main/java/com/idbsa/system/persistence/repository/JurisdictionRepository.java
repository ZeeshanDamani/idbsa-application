package com.idbsa.system.persistence.repository;

import com.idbsa.system.persistence.jpa.Jurisdiction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JurisdictionRepository extends JpaRepository<Jurisdiction, Integer> {

}
