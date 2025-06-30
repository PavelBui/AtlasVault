package com.bui.projects.service.impl;

import com.bui.projects.mapper.CountryMapper;
import com.bui.projects.repository.CountryRepository;
import com.bui.projects.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryMapper countryMapper;
    private CountryRepository countryRepository;

    @Override
    @Transactional
    public List<String> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(entity -> countryMapper.entityToDto(entity))
                .toList();
    }
}
