package com.bui.projects.repository;

import com.bui.projects.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer> {

    Optional<PublisherEntity> findByName(String name);
}
