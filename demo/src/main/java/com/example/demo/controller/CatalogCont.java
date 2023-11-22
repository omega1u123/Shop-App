package com.example.demo.controller;

import com.example.demo.model.CartEntity;
import com.example.demo.model.ItemEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.model.repository.CartRepo;
import com.example.demo.model.repository.ItemRepo;
import com.example.demo.model.repository.StoreRepo;
import com.example.demo.model.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("/catalog")
public class CatalogCont {

    private final UserRepo userRepo;
    private final ItemRepo itemRepo;

    private final StoreRepo storeRepo;
    private final CartRepo cartRepo;


    public CatalogCont(UserRepo userRepo, ItemRepo itemRepo, StoreRepo storeRepo, CartRepo cartRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.storeRepo = storeRepo;
        this.cartRepo = cartRepo;
    }


    @GetMapping("/user")
    public UserEntity getUser(){
        return userRepo.findUserEntityByLogin("user1");
    }


    @GetMapping("/catalogPage")
    public String getCartPage(Model model){
        CartEntity cart = new CartEntity();
        List<ItemEntity> items = new ArrayList<>();
        items.addAll(itemRepo.findAll());
        model.addAttribute("item", items);
        return "main_page";
    }


    @GetMapping("/create")
    public String create(){
        return "ok";
    }
}
