package com.idbsa.system.persistence.repository;


import com.idbsa.system.persistence.jpa.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {


}
