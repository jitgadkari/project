package com.animeserverside.animeseerverside.controller;

import com.animeserverside.animeseerverside.entity.Category;
import com.animeserverside.animeseerverside.payload.CategoryDto;
import com.animeserverside.animeseerverside.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
    CategoryDto category=this.categoryService.createCategory(categoryDto);
    return  new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @CrossOrigin
    @PutMapping("/{categoryId}")
    public  ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto category=this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/{categoryId}")
    public  void deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
    }
    @CrossOrigin
    @GetMapping()
    public  ResponseEntity<List<CategoryDto>> getAllCategories(){
       List<CategoryDto> categories = this.categoryService.getAllCategories();
       return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/{categoryId}")
    public  ResponseEntity<CategoryDto> getSingleCategories(@PathVariable Integer categoryId){
        CategoryDto category = this.categoryService.singleCategory(categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
}
