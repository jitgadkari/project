package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.entity.Anime;
import com.animeserverside.animeseerverside.entity.Category;
import com.animeserverside.animeseerverside.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto ,Integer categoryId);
    void deleteCategory(Integer categoryId);
    CategoryDto singleCategory(Integer catrgoyId);
    List<CategoryDto> getAllCategories();
}
