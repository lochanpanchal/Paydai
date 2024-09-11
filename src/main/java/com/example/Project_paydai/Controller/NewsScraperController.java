package com.example.Project_paydai.Controller;

import com.example.Project_paydai.Service.NewsScraperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class NewsScraperController {

    private final NewsScraperService newsScraperService;
//
//    @Autowired
//    public NewsScraperController(NewsScraperService newsScraperService) {
//        this.newsScraperService = newsScraperService;
//    }
//
//    @GetMapping("/search")
//    public Map<String, Object> searchNews(@RequestParam("query") String query) {
//        log.info("Received search request for query: {}", query);
//
//        List<String> newsUrls = newsScraperService.scrapeGoogleNews(query);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("urls", newsUrls);
//        log.info("Returning {} news URLs for query: {}", newsUrls.size(), query);
//
//        return response;
//    }
public NewsScraperController(NewsScraperService newsScraperService) {
    this.newsScraperService = newsScraperService;
}

    @GetMapping("/search")
    public List<Map<String, String>> searchNews(@RequestParam("query") String query) {
        return newsScraperService.scrapeGoogleNews(query);
    }

}

