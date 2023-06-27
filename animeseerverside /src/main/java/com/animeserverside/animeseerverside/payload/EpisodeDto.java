package com.animeserverside.animeseerverside.payload;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class EpisodeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer episodeId;
    private String episodeTitle;
    private String episodeLink;
    private Integer episodeNo;
}
