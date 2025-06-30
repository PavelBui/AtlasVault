package com.bui.projects.repository;

import com.bui.projects.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

        Optional<ImageEntity> findByPath(String path);
}
