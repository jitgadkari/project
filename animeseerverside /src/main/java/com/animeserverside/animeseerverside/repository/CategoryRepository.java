package com.animeserverside.animeseerverside.repository;

import com.animeserverside.animeseerverside.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
