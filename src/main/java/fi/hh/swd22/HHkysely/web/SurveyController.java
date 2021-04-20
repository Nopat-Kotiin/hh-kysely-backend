package fi.hh.swd22.HHkysely.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.swd22.HHkysely.domain.Survey;
import fi.hh.swd22.HHkysely.domain.SurveyRepository;
import fi.hh.swd22.HHkysely.domain.Question;
import fi.hh.swd22.HHkysely.domain.QuestionRepository;


@Controller
@CrossOrigin("*")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public String indexRedirect() {
        return "redirect:/surveys";
    }

    @GetMapping("/getsurveys")
    public @ResponseBody List<Survey> getRestSurveys() {
        return (List<Survey>) surveyRepository.findAll();
    }
    
    @RequestMapping(value = "/surveys", method = RequestMethod.GET)
	public String getSurveys (Model model) {
		List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
		model.addAttribute("surveys", surveys);
		return "surveys";
    }
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveSurvey(@ModelAttribute Survey survey) {
        surveyRepository.save(survey);

        for (Question q : survey.getQuestions()) {
            System.out.println(q);
            if (q.getQuestion() != null) {
                q.setSurvey(survey);
                questionRepository.save(q);
            }
        }

        for (Question q : questionRepository.findBySurveyId(survey.getId())) {
            if (!survey.getQuestions().contains(q)) {
                q.getSurvey().dismissQuestion(q);
                questionRepository.delete(q);
            }
        }
    	return "redirect:/surveys";
    }
    
    @GetMapping("addsurvey")
    public String addSurvey(Model model) {
    	boolean edit = false;
        List<Question> questions = new ArrayList<>();
        Survey survey = new Survey("");
        Question q = new Question("Kysymys 1", survey);
        questions.add(q);
        model.addAttribute("survey", survey);
        model.addAttribute("questions", questions);
        model.addAttribute("edit", edit);
        return "addsurvey";
    }

    @GetMapping("edit/{id}")
    public String editSurvey(@PathVariable("id") Long id, Model model) {
    	boolean edit = true;
        Survey survey = surveyRepository.findById(id).get();
        List<Question> questions = survey.getQuestions();
        model.addAttribute("survey", survey);
        model.addAttribute("questions", questions);
        model.addAttribute("edit", edit);
        return "addsurvey";
    }

    // Palauttaa No Content statuksen, jos kyselyä ei löydy
    @GetMapping("survey/{id}")
    public @ResponseBody ResponseEntity<Survey> getSurveyById(@PathVariable("id") Long id) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        Optional<Survey> s = surveyRepository.findById(id);
        Survey survey = new Survey();
        if (s.isPresent()) {
            survey = s.get();
            // Ei palauteta vastauksia tässä
            survey.getQuestions().forEach(q -> q.setAnswers(null));
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(survey, status);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteSurvey(@PathVariable("id") Long id) {
		surveyRepository.deleteById(id);
		return "redirect:../surveys";
	}
    
}
