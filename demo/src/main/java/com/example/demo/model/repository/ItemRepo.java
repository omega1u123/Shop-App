package com.example.demo.model.repository;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends CrudRepository<ItemEntity, Long> {

    List<ItemEntity> findAll();
    Optional<ItemEntity> findById(Long id);

    List<ItemEntity> findAllByCategory(CategoryEntity category);

}
