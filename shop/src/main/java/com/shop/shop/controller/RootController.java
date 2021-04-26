package com.shop.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class RootController {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @GetMapping
    public String index(Model model) {
        model.addAttribute("chartData", getChartData());
        return "tt";
    }

    private List<List<Object>> getChartData() {
        return List.of(
                List.of("Mushrooms", RANDOM.nextInt(5)),
                List.of("Onions", RANDOM.nextInt(5)),
                List.of("Olives", RANDOM.nextInt(5)),
                List.of("Zucchini", RANDOM.nextInt(5)),
                List.of("Pepperoni", RANDOM.nextInt(5))
        );
    }
}