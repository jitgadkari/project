package com.animeserverside.animeseerverside.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Episodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer episodeId;
    private String episodeTitle;
    private String episodeLink;
    private Integer episodeNo;
    @ManyToOne
    private Anime anime;
}
