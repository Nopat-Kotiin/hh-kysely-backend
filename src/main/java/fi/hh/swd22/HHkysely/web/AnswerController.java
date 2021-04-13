package fi.hh.swd22.HHkysely.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.hh.swd22.HHkysely.domain.Answer;
import fi.hh.swd22.HHkysely.domain.AnswerRepository;
import fi.hh.swd22.HHkysely.domain.Question;
import fi.hh.swd22.HHkysely.domain.QuestionRepository;
import fi.hh.swd22.HHkysely.domain.Survey;
import fi.hh.swd22.HHkysely.domain.SurveyRepository;

@RestController
@CrossOrigin("*")
public class AnswerController {
    
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping(value = "/submit", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> submitAnswers(@RequestBody Survey survey) {
        HttpStatus status = HttpStatus.OK;
        String resp = "answers submitted";

        if (!surveyRepository.findById(survey.getId()).isPresent()) {
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
}
