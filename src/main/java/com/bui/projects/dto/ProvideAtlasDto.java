package com.bui.projects.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvideAtlasDto {

    @NotNull(message = "title shouldn't be null")
    private String title;
    private String timePeriod;
    private String description;
    private String clazz;
    private Integer year;
    private String publisher;
    private String country;
    private Integer circulation;
}
