package com.bui.projects.controller;

import com.bui.projects.dto.AtlasDto;
import com.bui.projects.service.AtlasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/atlas")
@Api(tags = "Atlas Endpoints")
public class AtlasController {

    private final AtlasService atlasService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create Atlas")
    public ResponseEntity<AtlasDto> createAtlas(@RequestBody AtlasDto atlasDto) {
        atlasDto.setId(null);
        return ResponseEntity.ok(atlasService.createAtlas(atlasDto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Atlas by id")
    public ResponseEntity<AtlasDto> updateAtlas(@PathVariable Integer id, @RequestBody AtlasDto atlasDto) {
        atlasDto.setId(id);
        return ResponseEntity.ok(atlasService.updateAtlas(id, atlasDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete Atlas by id")
    public ResponseEntity<String> deleteAtlas(@PathVariable Integer id) {
        return ResponseEntity.ok(atlasService.deleteAtlas(id));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Atlas by id")
    public ResponseEntity<AtlasDto> getAtlas(@PathVariable Integer id) {
        return ResponseEntity.ok(atlasService.getAtlas(id));
    }

    @GetMapping
    @ApiOperation("Get list of all Atlases")
    public ResponseEntity<List<AtlasDto>> getAllAtlases() {
        return ResponseEntity.ok(atlasService.getAllAtlases());
    }

}
