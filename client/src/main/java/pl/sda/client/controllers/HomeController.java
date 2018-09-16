package pl.sda.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = "/menu")
    @GetMapping
    public String MenuPage(){

        return "HomePage";
    }


}
