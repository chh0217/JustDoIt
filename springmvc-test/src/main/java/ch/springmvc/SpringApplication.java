package ch.springmvc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author chenhang
 * @date 2020/3/24 下午5:21
 * @description
 */
@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(SpringApplication.class).build().run(args);
    }
}
