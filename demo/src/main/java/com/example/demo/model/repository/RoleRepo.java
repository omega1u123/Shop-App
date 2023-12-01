package com.example.demo.model.repository;

import com.example.demo.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityById(Long id);
}
