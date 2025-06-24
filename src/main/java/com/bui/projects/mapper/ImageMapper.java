package com.bui.projects.mapper;

import com.bui.projects.dto.ImageDto;
import com.bui.projects.entity.ImageEntity;
import com.bui.projects.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImageMapper {

    private ImageRepository imageRepository;

    public Set<ImageEntity> dtoToEntity(List<ImageDto> imageDtoList) {
        return imageDtoList.stream()
                .map(dto -> imageRepository.findByPath(dto.getPath())
                        .orElse(new ImageEntity(dto.getName(), dto.getPath()))).collect(Collectors.toSet());
    }
}
