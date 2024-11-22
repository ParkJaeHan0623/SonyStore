package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class searchController {
    
    @GetMapping("/search/search_result")
    public String searchResult() {
        return "search/search_result";
    }
}
