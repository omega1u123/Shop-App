package com.example.demo.model.repository;

import com.example.demo.model.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<CategoryEntity, Long> {

    public List<CategoryEntity> findAll();
    public CategoryEntity findCategoryEntityByName(String name);
}
