package com.bui.projects.service.impl;

import com.bui.projects.dto.GetAtlasDto;
import com.bui.projects.dto.ImageDto;
import com.bui.projects.entity.AtlasEntity;
import com.bui.projects.entity.ImageEntity;
import com.bui.projects.exeption.AtlasNotFoundException;
import com.bui.projects.exeption.ImageNotFoundException;
import com.bui.projects.mapper.ImageMapper;
import com.bui.projects.service.AtlasService;
import com.bui.projects.dto.ProvideAtlasDto;
import com.bui.projects.mapper.AtlasMapper;
import com.bui.projects.repository.AtlasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AtlasServiceImpl implements AtlasService {

    private AtlasMapper atlasMapper;
    private AtlasRepository atlasRepository;
    private ImageMapper imageMapper;

    @Override
    @Transactional
    public GetAtlasDto createAtlas(ProvideAtlasDto atlasDto) {
        AtlasEntity atlasEntity = atlasMapper.dtoToEntity(atlasDto);
        atlasRepository.save(atlasEntity);
        return atlasMapper.entityToDto(atlasEntity);
    }

    @Override
    @Transactional
    public GetAtlasDto updateAtlas(Integer id, ProvideAtlasDto atlasDto) {
        AtlasEntity atlasEntity = atlasRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AtlasNotFoundException(id));
        AtlasEntity updatedAtlasEntity = atlasMapper.dtoToEntity(atlasEntity, atlasDto);
        atlasRepository.save(updatedAtlasEntity);
        return atlasMapper.entityToDto(updatedAtlasEntity);
    }

    @Override
    @Transactional
    public String deleteAtlas(Integer id) {
        AtlasEntity atlasEntity = atlasRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AtlasNotFoundException(id));
        atlasEntity.setDeleted(true);
        atlasEntity.setDeleteDate(LocalDateTime.now());
        atlasRepository.save(atlasEntity);
        return "Atlas was deleted successfully";
    }

    @Override
    @Transactional
    public GetAtlasDto getAtlas(Integer id) {
        return atlasMapper.entityToDto(atlasRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AtlasNotFoundException((id))));
    }

    @Override
    @Transactional
    public List<GetAtlasDto> getAllAtlases() {
        return atlasRepository.findAllByIsDeletedFalse().stream()
                .map(entity -> atlasMapper.entityToDto(entity))
                .toList();
    }

    @Override
    @Transactional
    public void uploadImage(Integer id, ImageDto imageDto) {
        AtlasEntity atlasEntity = atlasRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AtlasNotFoundException((id)));
        atlasEntity.getImageEntities().add(imageMapper.dtoToEntity(imageDto));
        atlasRepository.save(atlasEntity);
    }

    @Override
    @Transactional
    public ImageDto getImage(Integer atlasId, Integer imageId) {
        AtlasEntity atlasEntity = atlasRepository.findByIdAndIsDeletedFalse(atlasId)
                .orElseThrow(() -> new AtlasNotFoundException((atlasId)));
        ImageEntity imageEntity = atlasEntity.getImageEntities().stream()
                .filter(entity -> entity.getId().equals(imageId))
                .findFirst().orElseThrow(() -> new ImageNotFoundException(atlasId, imageId));
        return imageMapper.entityToDto(imageEntity);
    }

    @Override
    @Transactional
    public List<ImageDto> getAllImages(Integer id) {
        AtlasEntity atlasEntity = atlasRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AtlasNotFoundException((id)));
        return atlasEntity.getImageEntities().stream()
                .map(entity -> imageMapper.entityToDto(entity))
                .toList();
    }
}
