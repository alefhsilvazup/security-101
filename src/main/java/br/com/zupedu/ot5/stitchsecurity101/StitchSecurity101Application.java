package br.com.zupedu.ot5.stitchsecurity101;

import br.com.zupedu.ot5.stitchsecurity101.usuario.Usuario;
import br.com.zupedu.ot5.stitchsecurity101.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StitchSecurity101Application {

	public static void main(String[] args) {
		SpringApplication.run(StitchSecurity101Application.class, args);
	}

	@Bean
	public CommandLineRunner criarUsuarios(UsuarioRepository usuarioRepository) {
		return (args -> {
			System.out.println("criando o usuário da nossa aplicação");
			usuarioRepository.save(
					new Usuario("stitch",
							new BCryptPasswordEncoder().encode("disney"))
			);
		});
	}
}
