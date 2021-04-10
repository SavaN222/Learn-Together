package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.api.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String index(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("https://api.quotable.io/random", Quote.class);

        model.addAttribute("quote", quote);
        return "index";
    }
}
