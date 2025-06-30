package com.bui.projects.mapper;

import com.bui.projects.dto.GetAtlasDto;
import com.bui.projects.dto.ProvideAtlasDto;
import com.bui.projects.entity.AtlasEntity;
import com.bui.projects.entity.ImageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class AtlasMapper {

    private PublisherMapper publisherMapper;
    private CountryMapper countryMapper;

    public AtlasEntity dtoToEntity(ProvideAtlasDto atlasDto) {
        AtlasEntity atlasEntity = new AtlasEntity();
        return dtoToEntity(atlasEntity, atlasDto);
    }

    public AtlasEntity dtoToEntity(AtlasEntity atlasEntity, ProvideAtlasDto atlasDto) {
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

    public GetAtlasDto entityToDto(AtlasEntity atlasEntity) {
        List<Integer> imageIdList = new ArrayList<>();
        Set<ImageEntity> imageEntitySet = atlasEntity.getImageEntities();
        if (imageEntitySet != null) {
            imageIdList = imageEntitySet.stream()
                    .map(ImageEntity::getId)
                    .toList();
        }
        return GetAtlasDto.builder()
                .id(atlasEntity.getId())
                .title(atlasEntity.getTitle())
                .timePeriod(atlasEntity.getTimePeriod())
                .description(atlasEntity.getDescription())
                .clazz(atlasEntity.getClazz())
                .year(atlasEntity.getYear())
                .publisher(atlasEntity.getPublisherEntity().getName())
                .country(atlasEntity.getCountryEntity().getName())
                .circulation(atlasEntity.getCirculation())
                .imagesIds(imageIdList)
                .build();
    }
}
