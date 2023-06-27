package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.entity.Category;
import com.animeserverside.animeseerverside.exception.ResourceNotFoundException;
import com.animeserverside.animeseerverside.payload.AnimeDto;
import com.animeserverside.animeseerverside.payload.CategoryDto;
import com.animeserverside.animeseerverside.repository.AnimeRepository;
import com.animeserverside.animeseerverside.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AnimeServiceImpl implements AnimeService{
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public AnimeDto createAnime(AnimeDto animeDto,Integer categoryId) {
        Category category= this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId" ,categoryId));
        Anime anime= this.modelMapper.map(animeDto,Anime.class);
        anime.setCategory(category);
        anime.setTrending(true);
        anime.setLatest(false);
        Anime anime1=this.animeRepository.save(anime);
        AnimeDto createdAnime =this.modelMapper.map(anime1,AnimeDto.class);
        return createdAnime;
    }

    @Override
    public AnimeDto updateAnime(AnimeDto animeDto, Integer animeId) {
        Anime anime =this.animeRepository.findById(animeId).orElseThrow(()->new ResourceNotFoundException("Anime","id",animeId));
        anime.setTitle(animeDto.getTitle());
        anime.setDetail(animeDto.getDetail());
        anime.setImdbId(animeDto.getImdbId());
        anime.setImg(animeDto.getImg());
        anime.setTrailerLink(animeDto.getTrailerLink());
        anime.setViews(animeDto.getViews());
        Anime updatedAnime=this.animeRepository.save(anime);
        return this.modelMapper.map(updatedAnime,AnimeDto.class);
    }

    @Override
    public void deleteAnime(Integer animeId) {
    Anime anime =this.animeRepository.findById(animeId).orElseThrow(()-> new ResourceNotFoundException("Anime","id",animeId));
    this.animeRepository.delete(anime);

    }

    @Override
    public AnimeDto getSingleAnime(Integer animeId) {
        Anime anime =this.animeRepository.findById(animeId).orElseThrow(()-> new ResourceNotFoundException("Anime","id",animeId));
        return this.modelMapper.map(anime,AnimeDto.class);
    }

    @Override
    public List<AnimeDto> getAllAnime() {
        List<Anime> animeList=this.animeRepository.findAll();
        List<AnimeDto> animeDtoList= animeList.stream().map((animelist)-> this.modelMapper.map(animelist,AnimeDto.class)).collect(Collectors.toList());
        return animeDtoList;
    }

    @Override
    public List<AnimeDto> getAnimeByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        List<Anime> anime=this.animeRepository.findByCategory(category);
        List<AnimeDto> animeDto= anime.stream().map((ani)->this.modelMapper.map(ani,AnimeDto.class)).collect(Collectors.toList());
        return animeDto;
    }

    @Override
    public List<AnimeDto> searchAnime(String keyword) {
    List<Anime> anime=this.animeRepository.findByTitleContaining(keyword);
    List<AnimeDto> animeSearch = anime.stream().map((ani)->this.modelMapper.map(ani,AnimeDto.class)).collect(Collectors.toList());
        return animeSearch;
    }

//    @Override
//    public AnimeDto updateViews(Integer animeId, Integer views) {
//        Anime anime =this.animeRepository.findById(animeId).orElseThrow(()-> new ResourceNotFoundException("Anime","id",animeId));
//        anime.setViews(views);
//        Anime updatedAnime = this.animeRepository.save(anime);
//        return this.modelMapper.map(updatedAnime,AnimeDto.class);
//    }
    public AnimeDto updateAnimeviews(Integer animeId, Map<String, Object> fields) {
        Optional<Anime> existingAnime = this.animeRepository.findById(animeId);

        if (existingAnime.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Anime.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingAnime.get(), value);
            });
            Anime anime=this.animeRepository.save(existingAnime.get());
            return this.modelMapper.map(anime,AnimeDto.class);
        }
        return null;
    }
}
