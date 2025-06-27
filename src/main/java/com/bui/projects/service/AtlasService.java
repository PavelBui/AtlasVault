package com.bui.projects.service;

import com.bui.projects.dto.GetAtlasDto;
import com.bui.projects.dto.ProvideAtlasDto;
import com.bui.projects.dto.ImageDto;

import java.util.List;

public interface AtlasService {

    GetAtlasDto createAtlas(ProvideAtlasDto taskDto);

    GetAtlasDto updateAtlas(Integer id, ProvideAtlasDto atlasDto);

    String deleteAtlas(Integer id);

    GetAtlasDto getAtlas(Integer id);

    List<GetAtlasDto> getAllAtlases();

    void uploadImage(Integer id, ImageDto imageDto);

    ImageDto getImage(Integer atlasId, Integer imageId);

    List<ImageDto> getAllImages(Integer id);
}
