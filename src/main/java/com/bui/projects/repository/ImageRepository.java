package com.bui.projects.repository;

import com.bui.projects.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {

        Optional<ImageEntity> findByPath(String path);
}
