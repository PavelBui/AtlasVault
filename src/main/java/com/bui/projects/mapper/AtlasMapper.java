package com.bui.projects.mapper;

import com.bui.projects.dto.AtlasDto;
import com.bui.projects.entity.AtlasEntity;
import com.bui.projects.entity.ImageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AtlasMapper {

    private PublisherMapper publisherMapper;
    private CountryMapper countryMapper;

    public AtlasEntity dtoToEntity(AtlasDto atlasDto) {
        AtlasEntity atlasEntity = new AtlasEntity();
        return dtoToEntity(atlasEntity, atlasDto);
    }

    public AtlasEntity dtoToEntity(AtlasEntity atlasEntity, AtlasDto atlasDto) {
        atlasEntity.setTitle(atlasDto.getTitle());
        atlasEntity.setTimePeriod(atlasDto.getTimePeriod());
        atlasEntity.setDescription(atlasDto.getDescription());
        atlasEntity.setClazz(atlasDto.getClazz());
        atlasEntity.setYear(atlasDto.getYear());
        atlasEntity.setCirculation(atlasDto.getCirculation());
        //Publisher
        atlasEntity.setPublisherEntity(publisherMapper.dtoToEntity(atlasDto.getPublisher()));
        //Country
        atlasEntity.setCountryEntity(countryMapper.dtoToEntity(atlasDto.getCountry()));
        return atlasEntity;
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
                .imagesIds(atlasEntity.getImageEntities().stream()
                        .map(ImageEntity::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
