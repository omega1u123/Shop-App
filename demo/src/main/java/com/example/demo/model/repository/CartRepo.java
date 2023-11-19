package com.example.demo.model.repository;

import com.example.demo.model.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends CrudRepository<CartEntity, Long> {

    public List<CartEntity> findAllByUserId(Long id);

    public CartEntity findCartEntityByUser_Id(Long userID);
}
