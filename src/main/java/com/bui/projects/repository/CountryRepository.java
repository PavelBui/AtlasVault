package com.bui.projects.repository;

import com.bui.projects.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

    Optional<CountryEntity> findByName(String name);
}
