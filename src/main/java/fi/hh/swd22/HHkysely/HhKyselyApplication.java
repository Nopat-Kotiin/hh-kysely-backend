package fi.hh.swd22.HHkysely;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd22.HHkysely.domain.Kysely;
import fi.hh.swd22.HHkysely.domain.KyselyRepository;
import fi.hh.swd22.HHkysely.domain.Kysymys;
import fi.hh.swd22.HHkysely.domain.KysymysRepository;

@SpringBootApplication
public class HhKyselyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhKyselyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
		return (args) -> {
			Kysely kysely1 = new Kysely("Testi 1");
			kyselyRepository.save(kysely1);
			kyselyRepository.save(new Kysely("Testi 2"));
			kyselyRepository.save(new Kysely("Testi 3"));
			kyselyRepository.save(new Kysely("Testi 4"));
			kyselyRepository.save(new Kysely("Testi 5"));

			Kysymys kysymys1 = new Kysymys("Kysymys 1", kysely1);
			kysymysRepository.save(kysymys1);
		};
	}

}
