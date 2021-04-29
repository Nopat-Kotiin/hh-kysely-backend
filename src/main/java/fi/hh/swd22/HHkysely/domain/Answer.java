package fi.hh.swd22.HHkysely.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.List;

import javax.persistence.ElementCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: mahdollisesti lisää tapa erotella monivalintavastaukset
// Tällä hetkellä mahdollista tarkistaa kysymyksestä onko vastaus monivalinta
@Entity
public class Answer {
	
	@ElementCollection
	private List<Integer> selections;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String answer;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question question;

	public Answer() {
		super();
		this.answer = "";
		this.question = null;
	}

	public Answer(String answer, Question question) {
		this.answer = answer;
		this.question = question;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Integer> getSelections() {
		return selections;
	}

	public void setSelections(List<Integer> selections) {
		this.selections = selections;
	}	
	
}
