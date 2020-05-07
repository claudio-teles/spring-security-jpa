package claudioteles.com.github.springsecurityjpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import claudioteles.com.github.springsecurityjpa.models.User;
import claudioteles.com.github.springsecurityjpa.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringSecurityJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJpaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner inicializarUser(UserRepository userRepository) {
		return (args) -> {
			int quantidadeDeUsers = (int) userRepository.count();
			if (quantidadeDeUsers == 0) {
				userRepository.save(new User("Admin Um", "123", true, "ROLE_ADMIN"));
				userRepository.save(new User("Admin Dois", "234", true, "ROLE_ADMIN"));
				
				userRepository.save(new User("Usuário Um", "345", true, "ROLE_USER"));
				userRepository.save(new User("Usuário Dois", "567", true, "ROLE_USER"));
				
				userRepository.save(new User("user", "pass", true, "ROLE_USER"));
			}
		};
	}

}
