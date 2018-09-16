package pl.sda.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.sda")
@EntityScan("pl.sda.commons.*")
public class ClientApplication {

	@Autowired
	PropertyLoader propertyLoader;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}

