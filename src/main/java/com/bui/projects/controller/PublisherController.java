package com.bui.projects.controller;

import com.bui.projects.service.PublisherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/publisher")
@Api(tags = "Publisher Endpoints")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    @ApiOperation("Get list of all Publishers")
    public ResponseEntity<List<String>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

}
