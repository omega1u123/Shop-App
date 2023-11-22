package com.example.demo.controller;

import com.example.demo.model.ItemEntity;
import com.example.demo.model.repository.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemCont {

    private final ItemRepo itemRepo;

    public ItemCont(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/getItem")
    public String getItem(@RequestParam("id") Long id, Model model){
        Optional<ItemEntity> item = itemRepo.findById(id);
        model.addAttribute("item", item);
        return "item_page";
    }

}
