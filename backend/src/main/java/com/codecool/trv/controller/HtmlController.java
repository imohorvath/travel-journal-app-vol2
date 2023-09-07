package com.codecool.trv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping(value= {"/main", "/journals/{journalId}"})
    public String loadMainPage() {
        return "index";
    }

}
