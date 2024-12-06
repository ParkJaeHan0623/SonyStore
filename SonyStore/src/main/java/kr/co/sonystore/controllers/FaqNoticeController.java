package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FaqNoticeController {
    @GetMapping("/faq-notice")
    public String faqNotice() {
        return "faq-notice/faq_notice";
    }
    
}
