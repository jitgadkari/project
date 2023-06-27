package com.animeserverside.animeseerverside.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "title")
    private String title;
    @Column(name="description")
    private  String description;
    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Anime> animes= new ArrayList<>();

}
