package com.animeserverside.animeseerverside.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animeId;
    private String title;
    private  String  imdbId;
    private  String trailerLink;
    private  String img;
    private  String  detail;
    private  Integer views;
    private  String poster;
    private  String trendingPosition;
    private  Boolean trending;
    private  Boolean latest;
    @ManyToOne
    @JoinColumn(name = "category")
    private  Category category;

    @OneToMany(mappedBy = "anime",cascade = CascadeType.ALL)
    private List<Comment> comment;
    @OneToMany(mappedBy = "anime",cascade = CascadeType.ALL)
    private List<Episodes> episodes;
}
