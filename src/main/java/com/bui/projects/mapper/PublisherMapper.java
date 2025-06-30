package com.bui.projects.mapper;

import com.bui.projects.entity.PublisherEntity;
import com.bui.projects.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PublisherMapper {

    private PublisherRepository publisherRepository;

    public PublisherEntity dtoToEntity(String publisherName) {
        return publisherRepository.findByName(publisherName).orElseGet(() -> publisherRepository.save(new PublisherEntity(publisherName)));
    }
}
