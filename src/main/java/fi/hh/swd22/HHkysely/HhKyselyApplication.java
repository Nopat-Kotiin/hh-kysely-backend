package fi.hh.swd22.HHkysely;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd22.HHkysely.domain.Survey;
import fi.hh.swd22.HHkysely.domain.SurveyRepository;
import fi.hh.swd22.HHkysely.domain.TextQuestion;
import fi.hh.swd22.HHkysely.domain.RadioQuestion;
import fi.hh.swd22.HHkysely.domain.CheckboxQuestion;
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

			TextQuestion q1 = new TextQuestion("Mikä on lempiruokasi?", esimerkkiKysely);
			TextQuestion q2 = new TextQuestion("Mikä on lempijuomasi?", esimerkkiKysely);
			RadioQuestion q3 = new RadioQuestion("Mikä on lempivärisi?", esimerkkiKysely);
			q3.getChoices().add("Sininen");
			q3.getChoices().add("Punainen");
			q3.getChoices().add("Keltainen");
			q3.getChoices().add("Vihreä");
			q3.getChoices().add("Oranssi");
			q3.getChoices().add("Violetti");
			q3.getChoices().add("Musta");
			q3.getChoices().add("Pinkki");
			CheckboxQuestion q4 = new CheckboxQuestion("Erikoisruokavaliotarpeet?", esimerkkiKysely);
			q4.getChoices().add("Gluteenitot");
			q4.getChoices().add("Maidoton");
			q4.getChoices().add("Latkoositon");
			q4.getChoices().add("Kasvis");
			q4.getChoices().add("Vegaani");
			q4.getChoices().add("Ei mikään");
			questionRepository.save(q1);
			questionRepository.save(q2);
			questionRepository.save(q3);
			questionRepository.save(q4);
		};
	}
}
