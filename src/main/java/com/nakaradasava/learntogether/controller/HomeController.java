package com.nakaradasava.learntogether.controller;

import com.nakaradasava.learntogether.entity.api.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    /**
     * Home page with quote api
     * @param model
     * @return home page
     */
    @GetMapping("/")
    public String index(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote;

        try {
            quote = restTemplate.getForObject("https://api.quotable.io/random", Quote.class);
        } catch (Exception exception) {
            quote = new Quote("Dalai Lama", "Share your knowledge. It is a way to achieve immortality.");
        }

        model.addAttribute("quote", quote);
        return "index";
    }
}