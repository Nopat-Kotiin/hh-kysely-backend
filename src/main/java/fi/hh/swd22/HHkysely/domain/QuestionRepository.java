package fi.hh.swd22.HHkysely.domain;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findBySurveyId(Long id);
}