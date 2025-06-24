package com.bui.projects.mapper;

import com.bui.projects.dto.AtlasDto;
import com.bui.projects.dto.ImageDto;
import com.bui.projects.entity.AtlasEntity;
import org.springframework.stereotype.Component;

@Component
public class AtlasMapper {

    public AtlasEntity dtoToEntity(AtlasDto atlasDto) {

        return new AtlasEntity();
    }

    public AtlasDto entityToDto(AtlasEntity atlasEntity) {
        return AtlasDto.builder()
                .id(atlasEntity.getId())
                .title(atlasEntity.getTitle())
                .timePeriod(atlasEntity.getTimePeriod())
                .description(atlasEntity.getDescription())
                .clazz(atlasEntity.getClazz())
                .year(atlasEntity.getYear())
                .publisher(atlasEntity.getPublisherEntity().getName())
                .country(atlasEntity.getCountryEntity().getName())
                .circulation(atlasEntity.getCirculation())
                .imageDtoList(atlasEntity.getImageEntities().stream().map(entity -> new ImageDto(entity.getId(), entity.getName(), entity.getPath())).toList())
                .build();
    }
}
