package pl.edu.wszib.glucoprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:db.properties")

@SpringBootApplication
public class GlucoProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlucoProfileApplication.class, args);
    }

}
