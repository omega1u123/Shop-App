package com.example.demo.controller;

import com.example.demo.model.ItemEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.model.repository.RoleRepo;
import com.example.demo.model.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegCont {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public RegCont(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @GetMapping("/regPage")
    public String getRegPage(Model model){
        UserEntity userForm = new UserEntity();
        model.addAttribute("userForm", userForm);
        return "registration";
    }

    @PostMapping("/regSave")
    public String saveReg(Model model, @ModelAttribute("userForm") UserEntity userForm){
        UserEntity user = new UserEntity(userForm.getLogin(), userForm.getPassword());
        user.setRole(roleRepo.findRoleEntityById(1L));
        System.out.println(user.getLogin());
        userRepo.save(user);
        return "redirect:/auth/loginPage";
    }

}
