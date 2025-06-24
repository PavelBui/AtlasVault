package com.bui.projects.mapper;

import com.bui.projects.dto.AtlasDto;
import com.bui.projects.dto.ImageDto;
import com.bui.projects.entity.AtlasEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AtlasMapper {

    private PublisherMapper publisherMapper;
    private CountryMapper countryMapper;
    private ImageMapper imageMapper;

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
        //Images
        atlasEntity.setImageEntities(imageMapper.dtoToEntity(atlasDto.getImageDtoList()));
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
                .imageDtoList(atlasEntity.getImageEntities().stream()
                        .map(entity -> new ImageDto(entity.getName(), entity.getPath())).toList())
                .build();
    }
}
