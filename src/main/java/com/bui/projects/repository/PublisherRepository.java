package com.bui.projects.repository;

import com.bui.projects.entity.PublisherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity, Integer> {

    Optional<PublisherEntity> findByName(String name);
}
