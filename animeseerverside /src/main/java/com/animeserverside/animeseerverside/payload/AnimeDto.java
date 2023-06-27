package com.animeserverside.animeseerverside.payload;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animeId;
    private String title;
    private  String  imdbId;
    private  String trailerLink;
    private  String img;
    private  String  detail;
    private  Integer views;
    private String poster;
    private  String trendingPosition;
    private CategoryDto category;
    private List<CommentDto> comment;
    private List<EpisodeDto> episodes;



}

