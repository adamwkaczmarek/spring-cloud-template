package home.samples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@Controller
public class AboutApplication {

    @Value("${example.property}")
    private String exampleProperty;


    @RequestMapping("/about")
    @ResponseBody
    String about() {
        return "Some information about project . Example property from config server : "+exampleProperty;
    }

    public static void main(String[] args) {
        SpringApplication.run(AboutApplication.class, args);
    }
}
