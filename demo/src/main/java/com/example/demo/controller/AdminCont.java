package com.example.demo.controller;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ItemEntity;
import com.example.demo.model.repository.CategoryRepo;
import com.example.demo.model.repository.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCont {

    private final CategoryRepo categoryRepo;
    private final ItemRepo itemRepo;

    public AdminCont(CategoryRepo categoryRepo, ItemRepo itemRepo) {
        this.categoryRepo = categoryRepo;
        this.itemRepo = itemRepo;
    }

    @GetMapping("/adminPage")
    public String getAdminPage(Model model){
        return "admin_page";
    }

    @GetMapping("/addItemPage")
    public String getAddItemPage(Model model){
        List<CategoryEntity> category = new ArrayList<>();
        category.addAll(categoryRepo.findAll());
        model.addAttribute("category", category);
        return "add_item_page";
    }

    @PostMapping("/addItem")
    public String addItem(
            @RequestParam String imageUrl,
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam float price,
            @RequestParam String description
    ){
        ItemEntity item = new ItemEntity();
        item.setId(15L);
        item.setImageUrl(imageUrl);
        item.setName(name);
        item.setCategory(categoryRepo.findCategoryEntityByName(category));
        item.setPrice(price);
        item.setDescription(description);
        itemRepo.save(item);
        return "admin_page";
    }

}
