package com.bui.projects.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class ImageEntity {

    public ImageEntity(String name, String path, byte[] imageData) {
        this.name = name;
        this.path = path;
        this.imageData = imageData;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @JsonIgnore
    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;
}
