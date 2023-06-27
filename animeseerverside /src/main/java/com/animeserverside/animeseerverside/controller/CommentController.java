package com.animeserverside.animeseerverside.controller;

import com.animeserverside.animeseerverside.payload.CommentDto;
import com.animeserverside.animeseerverside.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @CrossOrigin
    @PostMapping("/{userId}/{animeId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer animeId,@PathVariable Integer userId){
      CommentDto commentDto1= this.commentService.createComment(commentDto,animeId,userId);
      return new ResponseEntity<>(commentDto1, HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
    }

    @CrossOrigin
    @GetMapping("/{animeId}")
    public  ResponseEntity<List<CommentDto>> getCommentByAnime(@PathVariable Integer animeId){
        List<CommentDto> commentDtoList=this.commentService.getCommentByAnime(animeId);
        return new ResponseEntity<>(commentDtoList,HttpStatus.OK);
    }
}
