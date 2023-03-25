package hkmu.comps380f.controller;

import hkmu.comps380f.dao.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Resource
    private UserService userService;

    @GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("userDatabase", userService.getUsers());
        return "index";
    }
}

