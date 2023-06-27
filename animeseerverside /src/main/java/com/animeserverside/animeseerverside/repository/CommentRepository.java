package com.animeserverside.animeseerverside.repository;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.entity.Category;
import com.animeserverside.animeseerverside.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByAnime(Anime anime);
}
