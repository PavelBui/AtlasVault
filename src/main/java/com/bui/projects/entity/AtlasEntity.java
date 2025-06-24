package com.bui.projects.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "atlas")
public class AtlasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "time_period")
    private String timePeriod;

    @Column(name = "description")
    private String description;

    @Column(name = "class")
    private String clazz;

    @Column(name = "year")
    private Integer year;

    @ManyToOne
    private PublisherEntity publisherEntity;

    @ManyToOne
    private CountryEntity countryEntity;

    @Column(name = "circulation")
    private Integer circulation;

    @Column(name = "deleted_datetime")
    private LocalDateTime deleteDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "atlases_images")
    private Set<ImageEntity> imageEntities;
}
