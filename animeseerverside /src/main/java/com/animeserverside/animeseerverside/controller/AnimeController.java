package com.animeserverside.animeseerverside.controller;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.payload.AnimeDto;
import com.animeserverside.animeseerverside.payload.CategoryDto;
import com.animeserverside.animeseerverside.service.AnimeService;
import com.animeserverside.animeseerverside.service.AnimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/anime")
public class AnimeController {
    @Autowired
    private AnimeService animeService;
    @CrossOrigin
    @PostMapping("category/{categoryId}")
    public ResponseEntity<AnimeDto> createAnime(@RequestBody AnimeDto animeDto , @PathVariable Integer categoryId){
        AnimeDto animeDto1= this.animeService.createAnime(animeDto,categoryId);
        return new ResponseEntity<>(animeDto1, HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping("category/{categoryId}")
    public  ResponseEntity<List<AnimeDto>> getAnimeByCategory(@PathVariable Integer categoryId){
        List<AnimeDto> animeDtoList= this.animeService.getAnimeByCategory(categoryId);
        return new ResponseEntity<>(animeDtoList,HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<AnimeDto>> getAllAnime(){
        List<AnimeDto> animes = this.animeService.getAllAnime();
        return new ResponseEntity<>(animes,HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/{animeId}")
    public  ResponseEntity<AnimeDto> getAnimeById(@PathVariable Integer animeId){
        AnimeDto anime=this.animeService.getSingleAnime(animeId);
        return new ResponseEntity<>(anime,HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/{animeId}")
    public void deleteAnimeById(@PathVariable Integer animeId){
        this.animeService.deleteAnime(animeId);
    }

    @CrossOrigin
    @PutMapping("/{animeId}")
    public  ResponseEntity<AnimeDto> updateAnime(@RequestBody AnimeDto animeDto,@PathVariable Integer animeId){
       AnimeDto anime= this.animeService.updateAnime(animeDto,animeId);
       return  new ResponseEntity<>(anime,HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<AnimeDto>> searchAnime(@PathVariable String keyword){
     List<AnimeDto> anime=this.animeService.searchAnime(keyword);
     return new ResponseEntity<List<AnimeDto>>(anime,HttpStatus.OK);
    }
    @CrossOrigin
    @PatchMapping("/{animeId}")
    public  ResponseEntity<AnimeDto> updateView(@PathVariable Integer animeId, @RequestBody Map<String,Object>fields){
       AnimeDto anime= this.animeService.updateAnimeviews(animeId,fields);
       return new ResponseEntity<>(anime,HttpStatus.OK);
    }
}
