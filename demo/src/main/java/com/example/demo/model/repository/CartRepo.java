package com.example.demo.model.repository;

import com.example.demo.model.CartEntity;
import com.example.demo.model.ItemEntity;
import com.example.demo.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends CrudRepository<CartEntity, Long> {

    List<CartEntity> findAllByUserId(Long id);

    List<CartEntity> findCartEntityByItem(ItemEntity item);

    List<CartEntity> findACartEntityByItemAndUser(ItemEntity items, UserEntity user);


}
