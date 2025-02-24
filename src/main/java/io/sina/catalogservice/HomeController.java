package io.sina.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinaaskarnejad
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String getGreeting() {
        return "Hello World!";
    }
}
