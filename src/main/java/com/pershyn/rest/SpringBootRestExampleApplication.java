package com.pershyn.rest;

import com.pershyn.rest.storage_properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class SpringBootRestExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestExampleApplication.class, args);
    }

}

