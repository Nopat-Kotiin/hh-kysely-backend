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
			Survey kysely2 = new Survey("Testi 2");
			Survey kysely3 = new Survey("Testi 3");
			Survey kysely4 = new Survey("Testi 4");
			Survey kysely5 = new Survey("Testi 5");
			
			surveyRepository.save(kysely1);
			surveyRepository.save(kysely2);
			surveyRepository.save(kysely3);
			surveyRepository.save(kysely4);
			surveyRepository.save(kysely5);
			

			Question kysymys1 = new Question("Kysymys 1", kysely1);
			questionRepository.save(kysymys1);
			Question kysymys2 = new Question("Kysymys 2", kysely1);
			questionRepository.save(kysymys2);
			Question kysymys3 = new Question("Kysymys 1", kysely2);
			questionRepository.save(kysymys3);
			Question kysymys4 = new Question("Kysymys 2", kysely2);
			questionRepository.save(kysymys4);
			Question kysymys5 = new Question("Kysymys 1", kysely3);
			questionRepository.save(kysymys5);
			Question kysymys6 = new Question("Kysymys 2", kysely3);
			questionRepository.save(kysymys6);
			Question kysymys7 = new Question("Kysymys 1", kysely4);
			questionRepository.save(kysymys7);
			Question kysymys8 = new Question("Kysymys 1", kysely5);
			questionRepository.save(kysymys8);

			Answer answer = new Answer("Vastaus", kysymys1);
			answerRepository.save(answer);
			answerRepository.save(new Answer("Hei", kysymys2));
		};
	}

}
