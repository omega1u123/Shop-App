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
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/createCart")
    public void creatCart(){
        CartEntity cart = new CartEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        cart.setUser(userRepo.findUserEntityByLogin(auth.getName()));
        cart.setItemIds(itemRepo.findById(2L));
        cartRepo.save(cart);
        System.out.println(cart.toString());
    }

    @GetMapping("/getCart")
    public String getCart(Model model){
        List<CartEntity> cart = new ArrayList<>();
        List<Optional<ItemEntity>> items = new ArrayList<>();
        cart.addAll(cartRepo.findAllByUserId(1L));
        for(CartEntity cartEntity: cart){
            items.add(itemRepo.findById(cartEntity.getItemIds().getId()));
        }
        model.addAttribute("item", items);
        return "cart_page";
    }



}
