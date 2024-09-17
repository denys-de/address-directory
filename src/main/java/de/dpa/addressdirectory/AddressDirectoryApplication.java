package de.dpa.addressdirectory;

import de.dpa.addressdirectory.domain.person.PersonProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PersonProperties.class)
public class AddressDirectoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressDirectoryApplication.class, args);
    }

}
