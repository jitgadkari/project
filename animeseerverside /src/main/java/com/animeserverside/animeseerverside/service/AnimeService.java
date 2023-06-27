package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.payload.AnimeDto;
import com.animeserverside.animeseerverside.payload.CategoryDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.Map;

public interface AnimeService {

    AnimeDto createAnime(AnimeDto animeDto,Integer categoryId);
    AnimeDto updateAnime(AnimeDto animeDto,Integer animeId);
    void  deleteAnime(Integer animeId);
    AnimeDto getSingleAnime(Integer animeId);
    List<AnimeDto> getAllAnime();
    List<AnimeDto> getAnimeByCategory(Integer categoryId);
    List<AnimeDto> searchAnime(String keyword);
//    AnimeDto updateViews(Integer animeId,Integer views);
    AnimeDto updateAnimeviews(Integer animeId, Map<String, Object>fields);
}
