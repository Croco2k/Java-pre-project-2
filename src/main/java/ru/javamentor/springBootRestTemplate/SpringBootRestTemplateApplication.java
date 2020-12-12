package ru.javamentor.springBootRestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javamentor.springBootRestTemplate.model.User;
import ru.javamentor.springBootRestTemplate.service.RestTemplateServiceImpl;

@SpringBootApplication
public class SpringBootRestTemplateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestTemplateApplication.class, args);

	}


	@Autowired
	private RestTemplateServiceImpl restTemplateService;

	@Override
	public void run(String... args) throws Exception {
		User user = new User(3L, "James", "Brown", (byte) 23);
		restTemplateService.getUsers();

		restTemplateService.addUser(user);

		user.setName("Thomas");
		user.setLastName("Shelby");
		restTemplateService.updateUser(user);

		restTemplateService.deleteUser(3L);
	}
}
