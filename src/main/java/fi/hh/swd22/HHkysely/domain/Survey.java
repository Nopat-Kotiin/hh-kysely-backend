package fi.hh.swd22.HHkysely.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Survey {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String info;
	private String name;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
	private List<Question> questions;
		
	public Survey() {}
	
	public Survey(String name) {
		super();
		this.name = name;
		this.questions = new ArrayList<>();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(Question q) {
		this.questions.add(q);
	}

	public void dismissQuestion(Question q) {
		this.questions.remove(q);
	}

	public void dismissQuestions() {
	   this.questions.forEach(q -> q.dismissSurvey()); // SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP 
	   this.questions.clear();
	}

	@Override
	public String toString() {
		return "Kysely [kyselyId=" + id + ", nimi=" + name + ", kuvaus=" + info + "]";
	}
	
}
