package io.github.erick.backendCllientes;


import io.github.erick.backendCllientes.model.entity.Cliente;
import io.github.erick.backendCllientes.model.entity.Usuario;
import io.github.erick.backendCllientes.model.repository.ClienteRepository;
import io.github.erick.backendCllientes.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BackendCllientesApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Bean
	public CommandLineRunner init(){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				if (usuarioRepository.findById(1).isPresent()){

				}else {
					Usuario usuario = new Usuario();
					usuario.setUsername("Erick");
					usuario.setNomeCompleto("Erick Oliveira Santos");
					usuario.setPassword("admin");
					usuario.setCpf("06130716540");
					usuario.setDataNascimento(LocalDate.now());
					usuarioRepository.save(usuario);
				}

			}
		};
	}

	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {


		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendCllientesApplication.class, args);
	}

}
