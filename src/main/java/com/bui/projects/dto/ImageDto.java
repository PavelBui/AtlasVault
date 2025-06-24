package com.bui.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private Integer id;
    @NotNull(message = "name shouldn't be null")
    private String name;
    @NotNull(message = "path shouldn't be null")
    private String path;
}
