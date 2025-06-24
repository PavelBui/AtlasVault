package com.bui.projects.service.impl;

import com.bui.projects.entity.AtlasEntity;
import com.bui.projects.exeption.AtlasNotFoundException;
import com.bui.projects.service.AtlasService;
import com.bui.projects.dto.AtlasDto;
import com.bui.projects.mapper.AtlasMapper;
import com.bui.projects.repository.AtlasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AtlasServiceImpl implements AtlasService {

    private AtlasMapper atlasMapper;
    private AtlasRepository atlasRepository;

    @Override
    public AtlasDto createAtlas(AtlasDto atlasDto) {
        AtlasEntity atlasEntity = atlasMapper.dtoToEntity(atlasDto);
        atlasRepository.save(atlasEntity);
        return atlasMapper.entityToDto(atlasEntity);
    }

    @Override
    public AtlasDto updateAtlas(Integer id, AtlasDto atlasDto) {
        AtlasEntity atlasEntity = atlasRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AtlasNotFoundException(id));
        AtlasEntity updatedAtlasEntity = atlasMapper.dtoToEntity(atlasEntity, atlasDto);
        atlasRepository.save(updatedAtlasEntity);
        return atlasMapper.entityToDto(updatedAtlasEntity);
    }

    @Override
    public String deleteAtlas(Integer id) {

        return "Atlas was deleted successfully";
    }

    @Override
    public AtlasDto getAtlas(Integer id) {
        return atlasMapper.entityToDto(atlasRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AtlasNotFoundException((id))));
    }

    @Override
    public List<AtlasDto> getAllAtlases() {
        return atlasRepository.findAllByIsDeletedFalse().stream()
                .map(entity -> atlasMapper.entityToDto(entity))
                .toList();
    }
}
