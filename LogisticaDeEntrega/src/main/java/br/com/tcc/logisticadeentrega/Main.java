package br.com.tcc.logisticadeentrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"br.com.tcc.logisticadeentrega"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
