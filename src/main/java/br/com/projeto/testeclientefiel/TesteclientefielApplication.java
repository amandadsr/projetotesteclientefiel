package br.com.projeto.testeclientefiel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackages = "br.com.projeto.testeclientefiel.entity")
@ComponentScan(basePackages = "br.com.projeto*")
@EnableJpaRepositories(basePackages = "br.com.projeto.testeclientefiel.repository")
@EnableTransactionManagement
@SpringBootApplication
public class TesteclientefielApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteclientefielApplication.class, args);
	}

}
