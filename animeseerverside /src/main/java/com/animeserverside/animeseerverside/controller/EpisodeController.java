package com.animeserverside.animeseerverside.controller;

import com.animeserverside.animeseerverside.entity.Episodes;
import com.animeserverside.animeseerverside.payload.EpisodeDto;
import com.animeserverside.animeseerverside.service.EpisodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/episodes")
public class EpisodeController {
    @Autowired
    private EpisodesService episodesService;
    @PostMapping("/{animeId}")
    @CrossOrigin
    public ResponseEntity<EpisodeDto> createEpisodes(@RequestBody EpisodeDto episodeDto,@PathVariable Integer animeId){
      EpisodeDto episodeDto1=  this.episodesService.createEpisode(episodeDto,animeId);
      return new ResponseEntity<>(episodeDto1,HttpStatus.CREATED);
    }
    @GetMapping("/{animeId}")
    public ResponseEntity<List<EpisodeDto>> getAllEpisodes(@PathVariable Integer animeId){
      List<EpisodeDto> episodes= this.episodesService.getEpisodes(animeId);
      return  new ResponseEntity<>(episodes,HttpStatus.OK);
    }
    @DeleteMapping("/{episodeId}")
    @CrossOrigin
    public void  deleteEpisode(@PathVariable Integer episodeId){
        this.episodesService.deleteEpisodes(episodeId);
    }
}
