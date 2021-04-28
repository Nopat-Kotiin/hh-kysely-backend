package fi.hh.swd22.HHkysely.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.hh.swd22.HHkysely.domain.Answer;
import fi.hh.swd22.HHkysely.domain.AnswerRepository;
import fi.hh.swd22.HHkysely.domain.Question;
import fi.hh.swd22.HHkysely.domain.Survey;
import fi.hh.swd22.HHkysely.domain.SurveyRepository;

@RestController
@CrossOrigin("*")
public class AnswerController {
    
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    // Deprecated
    // TODO: REMOVE
    @PostMapping(value = "/submit", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> submitAnswers(@RequestBody Survey survey) {
        HttpStatus status = HttpStatus.OK;
        String resp = "answers submitted";

        // Jos vastaavaa kyselyä ei ole tai kysymyslistojen määrät ovat eri, palautetaan virhe
        // TODO: change teapot to a more suitable response
        if (!surveyRepository.findById(survey.getId()).isPresent() ||
            (survey.getQuestions().size() != surveyRepository.findById(survey.getId()).get().getQuestions().size())) {
            status = HttpStatus.I_AM_A_TEAPOT;
            resp = "I'm a teapot";
        } else {
            for (Question q : survey.getQuestions()) {
                for (Answer a : q.getAnswers()) {
                    answerRepository.save(a);
                }
            }
        }

        return new ResponseEntity<>(resp, status);
    }

    @PostMapping(value = "/surveys/{id}/answers", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> submitAnswers(@RequestBody List<Answer> answers, @PathVariable Long id) {
        Optional<Survey> surveyOpt = surveyRepository.findById(id);
        if (!surveyOpt.isPresent()) {
            return new ResponseEntity<>("Invalid survey", HttpStatus.NOT_FOUND);
        }

        Survey survey = surveyOpt.get();
        List<Question> questions = survey.getQuestions();

        if(questions.size() != answers.size()) {
            return new ResponseEntity<>("Error in input data", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        for (int i = 0; i < questions.size(); i++) {
            Answer a = answers.get(i);
            a.setQuestion(questions.get(i));
            answerRepository.save(a);
        }

        return new ResponseEntity<>("answers submitted", HttpStatus.OK);
    }

    // Palauttaa No Content statuksen, jos kyselyä ei löydy
    @GetMapping("getanswers/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable("id") Long id) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        Optional<Survey> s = surveyRepository.findById(id);
        Survey survey = new Survey();
        if (s.isPresent()) {
            survey = s.get();
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(survey, status);
    }
    
}
