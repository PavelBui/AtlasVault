package com.bui.projects.service;

import com.bui.projects.dto.AtlasDto;
import com.bui.projects.dto.ImageDto;

import java.util.List;

public interface AtlasService {

    AtlasDto createAtlas(AtlasDto taskDto);

    AtlasDto updateAtlas(Integer id, AtlasDto atlasDto);

    String deleteAtlas(Integer id);

    AtlasDto getAtlas(Integer id);

    List<AtlasDto> getAllAtlases();

    void uploadImage(Integer id, ImageDto imageDto);

    ImageDto getImage(Integer atlasId, Integer imageId);

    List<ImageDto> getAllImages(Integer id);
}
