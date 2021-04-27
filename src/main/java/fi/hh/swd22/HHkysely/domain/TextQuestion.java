package fi.hh.swd22.HHkysely.domain;

import javax.persistence.Entity;

@Entity
public class TextQuestion extends Question {

    public TextQuestion() {
        super();
        this.setType("text");
    }

    public TextQuestion(String question, Survey survey) {
        super("text", question, survey);
        this.setType("text");
    }

}
