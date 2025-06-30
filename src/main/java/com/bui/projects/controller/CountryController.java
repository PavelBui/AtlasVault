package com.bui.projects.controller;

import com.bui.projects.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/country")
@Api(tags = "Country Endpoints")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    @ApiOperation("Get list of all Countries")
    public ResponseEntity<List<String>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

}
