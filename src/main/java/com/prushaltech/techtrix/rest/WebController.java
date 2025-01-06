package com.prushaltech.techtrix.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/{path:[^\\.]*}") // Matches all paths except those with a dot (e.g., .js, .css)
    public String redirect() {
        return "forward:/index.html";
    }
}
