package fi.hh.swd22.HHkysely;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd22.HHkysely.domain.Survey;
import fi.hh.swd22.HHkysely.domain.SurveyRepository;
import fi.hh.swd22.HHkysely.domain.Answer;
import fi.hh.swd22.HHkysely.domain.AnswerRepository;
import fi.hh.swd22.HHkysely.domain.Question;
import fi.hh.swd22.HHkysely.domain.QuestionRepository;

@SpringBootApplication
public class HhKyselyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhKyselyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(SurveyRepository surveyRepository,
								QuestionRepository questionRepository,
								AnswerRepository answerRepository) {
		return (args) -> {
			Survey kysely1 = new Survey("Testi 1");
			surveyRepository.save(kysely1);
			surveyRepository.save(new Survey("Testi 2"));
			surveyRepository.save(new Survey("Testi 3"));
			surveyRepository.save(new Survey("Testi 4"));
			surveyRepository.save(new Survey("Testi 5"));

			Question kysymys1 = new Question("Kysymys 1", kysely1);
			questionRepository.save(kysymys1);
			Question kysymys2 = new Question("Kysymys 2", kysely1);
			questionRepository.save(kysymys2);

			Answer answer = new Answer("Vastaus", kysymys1);
			answerRepository.save(answer);
			answerRepository.save(new Answer("Hei", kysymys2));
		};
	}

}
