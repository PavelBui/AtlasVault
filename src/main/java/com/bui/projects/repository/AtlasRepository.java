package com.bui.projects.repository;

import com.bui.projects.entity.AtlasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtlasRepository extends JpaRepository<AtlasEntity, Integer> {

    Optional<AtlasEntity> findByIdAndIsDeletedFalse(Integer id);

    List<AtlasEntity> findAllByIsDeletedFalse();
}
