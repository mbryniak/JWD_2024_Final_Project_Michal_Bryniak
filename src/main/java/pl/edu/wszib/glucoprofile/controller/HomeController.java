package pl.edu.wszib.glucoprofile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Strona główna (layout.html)
    @GetMapping("/")
    public String showHomePage(Model model) {
        return "layout";  // Refers to layout.html
    }
}
