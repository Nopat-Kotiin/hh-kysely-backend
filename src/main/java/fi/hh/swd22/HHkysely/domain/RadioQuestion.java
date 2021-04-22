package fi.hh.swd22.HHkysely.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class RadioQuestion extends Question {

    @ElementCollection
    private List<String> choices;

    public RadioQuestion() {
        super();
        this.choices = new ArrayList<>();
        this.setType("radio");
    }

    public RadioQuestion(String question, Survey survey) {
        super("text", question, survey);
        this.choices = new ArrayList<>();
        this.setType("radio");
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
