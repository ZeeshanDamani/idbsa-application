package com.idbsa.system.persistence.repository;


import com.idbsa.system.persistence.jpa.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

}
