package com.bui.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAtlasDto {

    private Integer id;
    @NotNull(message = "title shouldn't be null")
    private String title;
    private String timePeriod;
    private String description;
    private String clazz;
    private Integer year;
    private String publisher;
    private String country;
    private Integer circulation;
    private List<Integer> imagesIds;
}
