package com.animeserverside.animeseerverside.repository;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.entity.Episodes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodesRepository extends JpaRepository<Episodes,Integer> {
    List<Episodes> findByAnime(Anime anime);
}
