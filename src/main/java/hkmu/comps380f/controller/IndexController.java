package hkmu.comps380f.controller;

import hkmu.comps380f.dao.PhotoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "redirect:/photo/index";

    }
}

