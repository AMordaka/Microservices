package pl.dmcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class HousingClient {

    public static void main(String[] args) {
        SpringApplication.run(HousingClient.class, args);
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello World";
    }


}
