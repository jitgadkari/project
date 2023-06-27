package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.entity.Episodes;
import com.animeserverside.animeseerverside.exception.ResourceNotFoundException;
import com.animeserverside.animeseerverside.payload.EpisodeDto;
import com.animeserverside.animeseerverside.repository.AnimeRepository;
import com.animeserverside.animeseerverside.repository.EpisodesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodesServiceImpl  implements  EpisodesService{
    @Autowired
    private EpisodesRepository episodesRepository;
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EpisodeDto createEpisode(EpisodeDto episodeDto, Integer animeId) {
        Anime anime = this.animeRepository.findById(animeId).orElseThrow(()-> new ResourceNotFoundException("anime","animeId",animeId));
        Episodes episodes=this.modelMapper.map(episodeDto,Episodes.class);
        episodes.setAnime(anime);
        Episodes newEpiosde=this.episodesRepository.save(episodes);
        return this.modelMapper.map(newEpiosde,EpisodeDto.class);
    }

    @Override
    public void deleteEpisodes(Integer episodeId) {
    Episodes episodes=this.episodesRepository.findById(episodeId).orElseThrow(()->new ResourceNotFoundException("episode","episodeId",episodeId));
    this.episodesRepository.delete(episodes);
    }

    @Override
    public List<EpisodeDto> getEpisodes(Integer animeId) {
        Anime anime=this.animeRepository.findById(animeId).orElseThrow(()->new ResourceNotFoundException("anime", "animeId",animeId));
        List<Episodes> episodesList=this.episodesRepository.findByAnime(anime);
        List<EpisodeDto> episodeDtoList=episodesList.stream().map((episodes)->this.modelMapper.map(episodes,EpisodeDto.class)).collect(Collectors.toList());
        return episodeDtoList;
    }
}
