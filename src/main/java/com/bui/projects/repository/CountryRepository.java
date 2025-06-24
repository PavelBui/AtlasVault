package com.bui.projects.repository;

import com.bui.projects.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {

    Optional<CountryEntity> findByName(String name);
}
