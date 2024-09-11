package com.example.Project_paydai.Service;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsScraperService {

    private static final Logger log = LoggerFactory.getLogger(NewsScraperService.class);

    //public List<String> scrapeGoogleNews(String searchTerm) {
    public List<Map<String, String>> scrapeGoogleNews(String searchTerm) {
        List<Map<String, String>> newsUrls = new ArrayList<>();
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();

            // Navigate to Google News
            page.navigate("https://news.google.com/");

            // Wait for the page to load
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);

            // Perform the search
            page.fill("input[type='text']", searchTerm);
            page.keyboard().press("Enter");

            // Wait for search results
            page.waitForSelector("article a[href]");

            // Scroll to load more results (optional)
            page.evaluate("window.scrollBy(0, window.innerHeight);");
            Thread.sleep(3000);

            // Extract the URLs from search results
            List<ElementHandle> linkElements = page.locator("article a[href]").elementHandles();

            // Limit the number of results to 5
            int limit = Math.min(5, linkElements.size());

            for (int i = 0; i < limit; i++) {
                ElementHandle element = linkElements.get(i);
                String href = element.getAttribute("href");
                if (href != null) {
                    // Convert relative URL to absolute if necessary
                    if (!href.startsWith("http")) {
                        href = "https://news.google.com" + href;
                    }

                    // Create a Map to store the URL as JSON format
                    Map<String, String> result = new HashMap<>();
                    result.put("url", href);

                    // Add the result to the list
                    newsUrls.add(result);
                }
            }

            // Close the browser
            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsUrls;
    }
}
