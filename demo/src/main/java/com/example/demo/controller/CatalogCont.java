package com.example.demo.controller;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ItemEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.model.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller()
@RequestMapping("/catalog")
public class CatalogCont {

    private final UserRepo userRepo;
    private final ItemRepo itemRepo;
    private final CategoryRepo categoryRepo;


    public CatalogCont(UserRepo userRepo, ItemRepo itemRepo, CategoryRepo categoryRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.categoryRepo = categoryRepo;
    }


    @GetMapping("/user")
    public UserEntity getUser(){
        return userRepo.findUserEntityByLogin("user1");
    }


    @GetMapping("/catalogPage")
    public String getCartPage(Model model){
        List<ItemEntity> items = new ArrayList<>();
        items.addAll(itemRepo.findAll());
        model.addAttribute("item", items);
        List<CategoryEntity> categories = new ArrayList<>();
        categories.addAll(categoryRepo.findAll());
        model.addAttribute("category", categories);
        return "main_page";
    }

    @GetMapping("/filteredPage")
    public String getFilteredPage(@RequestParam String category, Model model){
        List<ItemEntity> items = new ArrayList<>();
        items.addAll(itemRepo.findAllByCategory(categoryRepo.findCategoryEntityByName(category)));
        model.addAttribute("item", items);
        List<CategoryEntity> categories = new ArrayList<>();
        categories.addAll(categoryRepo.findAll());
        model.addAttribute("category", categories);
        return "main_page";
    }

    @GetMapping("/priceFilteredPage")
    public String getPriceFilteredPage(@RequestParam String param,Model model){
        List<CategoryEntity> categories = new ArrayList<>();
        categories.addAll(categoryRepo.findAll());
        model.addAttribute("category", categories);
        List<ItemEntity> items = new ArrayList<>();
        items.addAll(itemRepo.findAll());
        Collections.sort(items);
        if(param.equals("low")){
            Collections.reverse(items);
            model.addAttribute("item", items);
            return "main_page";
        }
        model.addAttribute("item", items);
        return "main_page";
    }


    @GetMapping("/create")
    public String create(){
        return "ok";
    }
}
