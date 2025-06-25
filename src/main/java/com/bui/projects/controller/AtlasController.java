package com.bui.projects.controller;

import com.bui.projects.dto.AtlasDto;
import com.bui.projects.dto.ImageDto;
import com.bui.projects.service.AtlasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/atlas")
@Api(tags = "Atlas Endpoints")
public class AtlasController {

    private static final String PREFIX_PATH = "images/";

    private final AtlasService atlasService;

    @PostMapping
    @ApiOperation("Create Atlas")
    public ResponseEntity<AtlasDto> createAtlas(@RequestBody AtlasDto atlasDto) {
        atlasDto.setId(null);
        return ResponseEntity.ok(atlasService.createAtlas(atlasDto));
    }

    @PostMapping("/{id}/image")
    @ApiOperation("Upload Image")
    public ResponseEntity<String> uploadImage(@PathVariable Integer id, @RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Image is empty");
        }

        if (!"image/jpeg".equalsIgnoreCase(Objects.requireNonNull(file.getContentType()))) {
            return ResponseEntity.badRequest().body("Only JPG files are allowed");
        }

        try {
            ImageDto imageDto = ImageDto.builder()
                    .name(file.getOriginalFilename())
                    .path(PREFIX_PATH + file.getOriginalFilename())
                    .imageBytes(file.getBytes())
                    .build();
            atlasService.uploadImage(id, imageDto);
            return ResponseEntity.ok("Image was uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process the file: " + file.getOriginalFilename());
        }
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

    @GetMapping("/{id}/image/{imageId}")
    @ApiOperation("Get image by imageId for Atlas by id")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id, @PathVariable Integer imageId) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(atlasService.getImage(id, imageId).getImageBytes());
    }

    @GetMapping("/{id}/images")
    @ApiOperation("Get all images for Atlas by id")
    public ResponseEntity<List<ImageDto>> getAllImages(@PathVariable Integer id) {
        return ResponseEntity.ok(atlasService.getAllImages(id));
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
