package com.main.hackatonjr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Page_controleur {
    @Autowired
    private Catalog catalogue;
    @Autowired
    private GameMap carte;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/Catalogue")
    public String requestMethodCatalogue(Model model) {

        model.addAttribute(catalogue);

        return "catalogue";
    }

    @RequestMapping("/Carte")
    public String requestMethodCarte(Model model) {

        model.addAttribute(carte);
        return "carte";
    }

}
