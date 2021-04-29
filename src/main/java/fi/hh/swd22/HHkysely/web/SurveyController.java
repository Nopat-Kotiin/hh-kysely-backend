package fi.hh.swd22.HHkysely.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.swd22.HHkysely.domain.Survey;
import fi.hh.swd22.HHkysely.domain.SurveyRepository;
import fi.hh.swd22.HHkysely.domain.TextQuestion;
import fi.hh.swd22.HHkysely.domain.RadioQuestion;
import fi.hh.swd22.HHkysely.domain.CheckboxQuestion;
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
        return "redirect:/surveylist";
    }

    // function based on:
    // https://stackoverflow.com/questions/37847549/spring-mvc-form-data-binding-of-a-list-of-abstract-class
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder, HttpServletRequest httpServletRequest){
        if (!"POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return;
        }

        Object nonCastedTarget = webDataBinder.getTarget();
        if (nonCastedTarget == null || !(nonCastedTarget instanceof Survey)) {
            return;
        }

        Pattern pattern = Pattern.compile("questions\\[(\\d+)]\\.type");

        Map<Integer, String> types = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String element = parameterNames.nextElement();
            Matcher matcher = pattern.matcher(element);
            if (!matcher.matches()) {
                continue;
            }

            types.put(Integer.parseInt(matcher.group(1)), httpServletRequest.getParameter(element));
        }

        Survey target = (Survey) nonCastedTarget;
        List<Question> questionList = target.getQuestions();
        if (questionList == null) {
            target.setQuestions(new ArrayList<>());
        }

        types.keySet().stream().sorted().forEach(key -> {
            switch (types.get(key)) {
                case "text":
                    target.getQuestions().add(new TextQuestion());
                    break;

                case "radio":
                    target.getQuestions().add(new RadioQuestion());
                    break;
                
                case "checkbox":
                	target.getQuestions().add(new CheckboxQuestion());
                	break;
                
                default:
                    throw new IllegalStateException("Unknown type: " + key);
            }
        });
    }

    @GetMapping("/surveys")
    public @ResponseBody List<Survey> getRestSurveys() {
        List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
        for (Survey s : surveys) {
            s.getQuestions().forEach(q -> q.setAnswers(null));
        }
        return (List<Survey>) surveyRepository.findAll();
    }
    
    @RequestMapping(value = "/surveylist", method = RequestMethod.GET)
	public String getSurveys (Model model) {
		List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
		model.addAttribute("surveys", surveys);
		return "surveys";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveSurvey(@ModelAttribute Survey survey) {

        List<Question> questions = survey.getQuestions();
        // Pitää asettaa tyhjä lista väliaikaisesti, ettei kysymyksiä tallenneta tuplana tietokantaan
        survey.setQuestions(new ArrayList<Question>());
        surveyRepository.save(survey);

        for (Question q : questions) {
            if (q.getQuestion() != null) {
                q.setSurvey(survey);
                questionRepository.save(q);
            }
        }

        for (Question q : questionRepository.findBySurveyId(survey.getId())) {
            if (!questions.contains(q)) {
                q.getSurvey().dismissQuestion(q);
                questionRepository.delete(q);
            }
        }
    	return "redirect:/surveys";
    }
    
    @GetMapping("/addsurvey")
    public String addSurvey(Model model) {
    	boolean edit = false;
        Survey survey = new Survey("");
        survey.getQuestions().add(new TextQuestion());
        model.addAttribute("survey", survey);
        model.addAttribute("edit", edit);
        return "addsurvey";
    }

    @GetMapping("/edit/{id}")
    public String editSurvey(@PathVariable("id") Long id, Model model) {
    	boolean edit = true;
        Survey survey = surveyRepository.findById(id).get();
        model.addAttribute("survey", survey);
        model.addAttribute("edit", edit);
        return "addsurvey";
    }

    // Palauttaa No Content statuksen, jos kyselyä ei löydy
    @GetMapping("/surveys/{id}")
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
