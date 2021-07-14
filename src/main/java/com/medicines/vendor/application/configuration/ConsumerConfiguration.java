package com.medicines.vendor.application.configuration;

import com.medicines.vendor.domain.users.Consumer;
import com.medicines.vendor.domain.users.repository.ConsumerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {
	@Bean
	CommandLineRunner consumerCLR(ConsumerRepository repository) {
		return args -> {
			var consumer = Consumer.builder()
				.name("Usuário teste")
				.CPF("081.532.790-09")
				.build();
			repository.save(consumer);
		};
	}
}
