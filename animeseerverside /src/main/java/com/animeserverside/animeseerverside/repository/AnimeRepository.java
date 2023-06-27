package com.animeserverside.animeseerverside.repository;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime,Integer> {
    List<Anime> findByCategory(Category category);
    List<Anime> findByTitleContaining(String title);

}
