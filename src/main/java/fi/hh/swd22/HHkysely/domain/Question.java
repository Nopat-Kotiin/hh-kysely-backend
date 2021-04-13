package fi.hh.swd22.HHkysely.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String question;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "surveyId")
	private Survey survey;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "answer")
	private List<Answer> answers;

	public Question() {}
	
	public Question(String question, Survey survey) {
		super();
		this.question = question;
		this.survey = survey;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Survey getSurvey() {
		return survey;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void dismissSurvey() {
        this.survey.dismissQuestion(this); //SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP
        this.survey = null;
    }

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Question)) {
			return false;
		}
		Question k = (Question) o;
		return id == k.id && question.equals(k.question) && Objects.equals(answers, k.answers);
	}

	@Override
	public String toString() {
		return "Kysymys [kysymysId=" + id + ", kysymys=" + question + ", kysely=" + survey + "]";
	}
}
