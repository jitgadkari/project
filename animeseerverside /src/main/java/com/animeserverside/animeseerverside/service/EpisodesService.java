package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.entity.Episodes;
import com.animeserverside.animeseerverside.payload.EpisodeDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EpisodesService {
    EpisodeDto createEpisode(EpisodeDto episodeDto,Integer animeId);
    void deleteEpisodes(Integer episodeId);
    List<EpisodeDto> getEpisodes(Integer animeId);

}
