package com.example.demo.controller;

import com.example.demo.model.CartEntity;
import com.example.demo.model.ItemEntity;
import com.example.demo.model.repository.CartRepo;
import com.example.demo.model.repository.ItemRepo;
import com.example.demo.model.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartCont {

    private final ItemRepo itemRepo;
    private final UserRepo userRepo;
    private final CartRepo cartRepo;

    public CartCont(ItemRepo itemRepo, UserRepo userRepo, CartRepo cartRepo) {
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
    }

   /* @GetMapping("/createCart")
    public void creatCart(){
        CartEntity cart = new CartEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        cart.setUser(userRepo.findUserEntityByLogin(auth.getName()));
        cart.setItemIds(itemRepo.findById(2L).get());
        cartRepo.save(cart);
    }*/


    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") Long id){
        CartEntity cart = new CartEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        cart.setUser(userRepo.findUserEntityByLogin(auth.getName()));
        cart.setItem(itemRepo.findById(id).get());
        cartRepo.save(cart);
        return "redirect:/item/getItem?id=" + id;
    }


    @GetMapping("/getCart")
    public String getCart(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<CartEntity> cart = new ArrayList<>();
        List<Optional<ItemEntity>> items = new ArrayList<>();
        cart.addAll(cartRepo.findAllByUserId(userRepo.findUserEntityByLogin(auth.getName()).getId()));
        for(CartEntity cartEntity: cart){
            items.add(itemRepo.findById(cartEntity.getItem().getId()));
        }
        model.addAttribute("item", items);
        float total = 0;
        for(Optional<ItemEntity> item : items){
            total+= item.get().getPrice();
        }
        model.addAttribute("total", total);
        return "cart_page";
    }

    @PostMapping("/deleteItemFromCart")
    public String deleteItemFromCart(@RequestParam Long id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<CartEntity> cartDelete = cartRepo.findACartEntityByItemAndUser(itemRepo.findById(id).get(), userRepo.findUserEntityByLogin(auth.getName()));
        cartRepo.delete(cartDelete.get(0));
        List<CartEntity> cart = new ArrayList<>();
        List<Optional<ItemEntity>> items = new ArrayList<>();
        cart.addAll(cartRepo.findAllByUserId(userRepo.findUserEntityByLogin(auth.getName()).getId()));
        for(CartEntity cartEntity: cart){
            items.add(itemRepo.findById(cartEntity.getItem().getId()));
        }
        model.addAttribute("item", items);
        float total = 0;
        for(Optional<ItemEntity> item : items){
            total+= item.get().getPrice();
        }
        model.addAttribute("total", total);
        return "cart_page";
    }



}
