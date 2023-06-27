package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.entity.Comment;
import com.animeserverside.animeseerverside.entity.User;
import com.animeserverside.animeseerverside.exception.ResourceNotFoundException;
import com.animeserverside.animeseerverside.payload.AnimeDto;
import com.animeserverside.animeseerverside.payload.CommentDto;
import com.animeserverside.animeseerverside.repository.AnimeRepository;
import com.animeserverside.animeseerverside.repository.CommentRepository;
import com.animeserverside.animeseerverside.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto,Integer animeId,Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException( "user","id",animeId));
        Anime anime =this.animeRepository.findById(animeId).orElseThrow(()-> new ResourceNotFoundException("anime","id",animeId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        comment.setAnime(anime);
        Comment savecomment =this.commentRepository.save(comment);
        CommentDto commentDto1=this.modelMapper.map(savecomment,CommentDto.class);
        return commentDto1;
    }

    @Override
    public void deleteComment(Integer commentId) {
       Comment comment= this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));
       this.commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentByAnime(Integer animeId) {
        Anime anime =this.animeRepository.findById(animeId).orElseThrow(()-> new ResourceNotFoundException("anime","id",animeId));
        List<Comment> comments=commentRepository.findByAnime(anime);
        List<CommentDto> commentDto= comments.stream().map((com)->this.modelMapper.map(com,CommentDto.class)).collect(Collectors.toList());
        return commentDto;
    }
}
