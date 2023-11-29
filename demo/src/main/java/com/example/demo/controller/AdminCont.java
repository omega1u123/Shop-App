package com.example.demo.controller;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.ItemEntity;
import com.example.demo.model.repository.CartRepo;
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

    private final CartRepo cartRepo;

    public AdminCont(CategoryRepo categoryRepo, ItemRepo itemRepo, CartRepo cartRepo) {
        this.categoryRepo = categoryRepo;
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
    }

    @GetMapping("/adminPage")
    public String getAdminPage(){
        return "admin_page";
    }

    @GetMapping("/addItemPage")
    public String getAddItemPage(Model model){
        List<CategoryEntity> category = new ArrayList<>(categoryRepo.findAll());
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


    @GetMapping("/getDeleteItemPage")
    public String getDeleteItemPage(Model model){
        List<ItemEntity> items = new ArrayList<>(itemRepo.findAll());
        model.addAttribute("item", items);
        List<CategoryEntity> categories = new ArrayList<>(categoryRepo.findAll());
        model.addAttribute("category", categories);
        return "delete_item_page";
    }


    @PostMapping("/deleteItem")
    public String deleteItem(@RequestParam Long id){
        ItemEntity item = itemRepo.findById(id).get();
        cartRepo.deleteAll(cartRepo.findCartEntityByItem(item));
        itemRepo.delete(item);
        return "redirect:/admin/getDeleteItemPage";
    }

}
