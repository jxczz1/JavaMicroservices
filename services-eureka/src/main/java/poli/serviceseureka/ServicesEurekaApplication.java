package poli.serviceseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServicesEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesEurekaApplication.class, args);
    }

}
