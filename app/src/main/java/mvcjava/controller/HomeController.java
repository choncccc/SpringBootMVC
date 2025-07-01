package mvcjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller                     // ← switch to @Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        // send an HTTP redirect (302) to /tasks
        return "redirect:/tasks";
    }
}
