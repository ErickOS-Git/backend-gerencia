package io.github.erick.backendCllientes;


import io.github.erick.backendCllientes.model.entity.Cliente;
import io.github.erick.backendCllientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendCllientesApplication {

	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {


		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendCllientesApplication.class, args);
	}

}
