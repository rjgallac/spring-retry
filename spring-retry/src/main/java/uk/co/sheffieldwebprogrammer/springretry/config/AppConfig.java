package uk.co.sheffieldwebprogrammer.springretry.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialRandomBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableRetry
public class AppConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialRandomBackOffPolicy expRandomBackOffPolicy = new ExponentialRandomBackOffPolicy();
        expRandomBackOffPolicy.setInitialInterval(1000);
        expRandomBackOffPolicy.setMaxInterval(15000);
        expRandomBackOffPolicy.setMultiplier(2);

        retryTemplate.setBackOffPolicy(expRandomBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(10);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
