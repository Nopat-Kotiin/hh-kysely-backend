package fi.hh.swd22.HHkysely;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd22.HHkysely.domain.Kysely;
import fi.hh.swd22.HHkysely.domain.KyselyRepository;

@SpringBootApplication
public class HhKyselyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhKyselyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(KyselyRepository kyselyRepository) {
		return (args) -> {
			kyselyRepository.save(new Kysely("Testi 1"));
			kyselyRepository.save(new Kysely("Testi 2"));
			kyselyRepository.save(new Kysely("Testi 3"));
			kyselyRepository.save(new Kysely("Testi 4"));
			kyselyRepository.save(new Kysely("Testi 5"));
		};
	}

}
