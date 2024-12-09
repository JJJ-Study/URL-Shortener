package com.f1v3.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 정승조
 * @version 2024. 12. 09.
 */
@Controller
public class ViewController {

    @GetMapping("/")
    public String mainPage() {
        return "url-shortener";
    }
}
