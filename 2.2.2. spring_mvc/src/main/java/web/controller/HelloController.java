package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.DAO;
import web.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    private DAO dao;

    @Autowired
    public HelloController(DAO dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        model.addAttribute("messages", messages);
        return "hello";
    }
    @ModelAttribute("newUser")
    public User getPerson(){
        return new User();
    }
    @GetMapping("/user")
	public String index(Model model){
    	model.addAttribute("user",dao.getAllUsers());
    	return "view/index";
	}

    @PostMapping("/user")
    public String creat(@ModelAttribute("newUser")@Valid User user,
                        BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("user",dao.getAllUsers());
            return "view/index";
        }
    	dao.saveUser(user);
    	return "redirect:/user";
    }

    @DeleteMapping("/user/{id}")
    public String deletePerson(@PathVariable("id") int id){
        dao.removeUserById(id);
        return "redirect:/user";
    }
    @GetMapping("/user/{id}/edit")
    public String edit (@ModelAttribute("id") int id,Model model){
        model.addAttribute("user",dao.getUserById(id));
        return "view/edit";
    }

    @PatchMapping("/user/{id}")
    public String updatePerson(@ModelAttribute("user")@Valid User updateuser, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "view/edit";
        }
        dao.updateUser(updateuser);
        return "redirect:/user";
    }
}