package uk.co.sheffieldwebprogrammer.springretry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private RetryTemplate retryTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String get(){
        retryTemplate.execute(arg0 -> {
            System.out.println("here");
            ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8081/test", String.class);
            System.out.println(forEntity.getBody());
            return null;
        });

        return "hi";
    }


}
