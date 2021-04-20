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
	public CommandLineRunner demo(SurveyRepository surveyRepository,
								QuestionRepository questionRepository) {
		return (args) -> {
			Survey esimerkkiKysely = new Survey("Lempikysely");
			esimerkkiKysely.setInfo("Tällä kyselyllä selvitetään tuttavien lempiruokia"
				+ " ja juomia, sekä lempivärejä tulevaa juhlaa varten.");
			surveyRepository.save(esimerkkiKysely);

			Question q1 = new Question("Mikä on lempiruokasi?", esimerkkiKysely);
			Question q2 = new Question("Mikä on lempijuomasi?", esimerkkiKysely);
			Question q3 = new Question("Mikä on lempivärisi?", esimerkkiKysely);
			questionRepository.save(q1);
			questionRepository.save(q2);
			questionRepository.save(q3);
		};
	}

}
