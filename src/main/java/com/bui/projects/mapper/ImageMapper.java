package com.bui.projects.mapper;

import com.bui.projects.dto.ImageDto;
import com.bui.projects.entity.ImageEntity;
import com.bui.projects.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ImageMapper {

    private ImageRepository imageRepository;

    public ImageEntity dtoToEntity(ImageDto imageDto) {
        return imageRepository.findByPath(imageDto.getPath())
                        .orElse(new ImageEntity(imageDto.getName(), imageDto.getPath(), imageDto.getImageBytes()));
    }

    public ImageDto entityToDto(ImageEntity imageEntity) {
        return ImageDto.builder()
                .name(imageEntity.getName())
                .path(imageEntity.getPath())
                .imageBytes(imageEntity.getImageData())
                .build();
    }
}
