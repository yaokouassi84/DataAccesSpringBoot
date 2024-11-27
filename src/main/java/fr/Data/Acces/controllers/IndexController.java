package fr.Data.Acces.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // Redirige vers le fichier index.html dans src/main/resources/static
        return "index";
    }
}
