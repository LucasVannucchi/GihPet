package br.com.banhoetosa.gihpet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GihpetApplication {

	public static void main(String[] args) {
		SpringApplication.run(GihpetApplication.class, args);
	}

}
