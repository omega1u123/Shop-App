package com.example.demo.model.repository;

import com.example.demo.model.StoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepo extends CrudRepository<StoreEntity, Long> {

    public StoreEntity findStoreEntityByName(String name);

    public List<StoreEntity> findAllBy();
}
