package com.example.demo.model.repository;

import com.example.demo.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

    public UserEntity findUserEntityByLogin(String login);

}
