package fi.hh.swd22.HHkysely;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd22.HHkysely.domain.Survey;
import fi.hh.swd22.HHkysely.domain.SurveyRepository;
import fi.hh.swd22.HHkysely.domain.Question;
import fi.hh.swd22.HHkysely.domain.QuestionRepository;

@SpringBootApplication
public class HhKyselyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhKyselyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(SurveyRepository kyselyRepository, QuestionRepository kysymysRepository) {
		return (args) -> {
			Survey kysely1 = new Survey("Testi 1");
			kyselyRepository.save(kysely1);
			kyselyRepository.save(new Survey("Testi 2"));
			kyselyRepository.save(new Survey("Testi 3"));
			kyselyRepository.save(new Survey("Testi 4"));
			kyselyRepository.save(new Survey("Testi 5"));

			Question kysymys1 = new Question("Kysymys 1", kysely1);
			kysymysRepository.save(kysymys1);
			Question kysymys2 = new Question("Kysymys 2", kysely1);
			kysymysRepository.save(kysymys2);
		};
	}

}
