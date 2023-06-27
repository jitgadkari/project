package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer animeId,Integer userId);
    void deleteComment(Integer commentId);

   List<CommentDto>  getCommentByAnime( Integer animeId);
}
