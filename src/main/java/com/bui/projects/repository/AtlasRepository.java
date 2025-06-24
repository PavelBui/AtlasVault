package com.bui.projects.repository;

import com.bui.projects.entity.AtlasEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtlasRepository extends CrudRepository<AtlasEntity, Integer> {

    Optional<AtlasEntity> findByIdAndIsDeletedFalse(Integer id);

    List<AtlasEntity> findAllByIsDeletedFalse();
}
