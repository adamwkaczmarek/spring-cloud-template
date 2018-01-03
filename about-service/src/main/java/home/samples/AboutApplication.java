package home.samples;


import home.samples.context.UserContextHolder;
import home.samples.context.UserContextInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@SpringBootApplication
@EnableEurekaClient
@Controller
public class AboutApplication {



    @Value("${example.property}")
    private String exampleProperty;


    @RequestMapping("/about")
    @ResponseBody
    String about() {
        return "Some information about project . Example property from config server : "+exampleProperty+
            "  Correlation ID : "+ UserContextHolder.getContext().getCorrelationId();
    }

    // HOW TO SET CORRELATION ID
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors==null){
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        }
        else{
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(AboutApplication.class, args);
    }
}
