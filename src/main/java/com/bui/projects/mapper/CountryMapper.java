package com.bui.projects.mapper;

import com.bui.projects.entity.CountryEntity;
import com.bui.projects.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CountryMapper {

    private CountryRepository countryRepository;

    public CountryEntity dtoToEntity(String countryName) {
        return countryRepository.findByName(countryName).orElseGet(() -> countryRepository.save(new CountryEntity(countryName)));
    }

    public String entityToDto(CountryEntity countryEntity) {
        return countryEntity.getName();
    }
}
