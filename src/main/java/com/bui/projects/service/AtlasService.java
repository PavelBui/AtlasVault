package com.bui.projects.service;

import com.bui.projects.dto.AtlasDto;

import java.util.List;

public interface AtlasService {

    AtlasDto createAtlas(AtlasDto taskDto);

    AtlasDto updateAtlas(Integer id, AtlasDto atlasDto);

    String deleteAtlas(Integer id);

    AtlasDto getAtlas(Integer id);

    List<AtlasDto> getAllAtlases();
}
