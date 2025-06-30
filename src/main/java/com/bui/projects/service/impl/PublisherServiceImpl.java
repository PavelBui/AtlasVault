package com.bui.projects.service.impl;

import com.bui.projects.mapper.PublisherMapper;
import com.bui.projects.repository.PublisherRepository;
import com.bui.projects.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private PublisherMapper publisherMapper;
    private PublisherRepository publisherRepository;

    @Override
    @Transactional
    public List<String> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(entity -> publisherMapper.entityToDto(entity))
                .toList();
    }
}
