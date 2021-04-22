package fi.hh.swd22.HHkysely.domain;

import javax.persistence.Entity;

@Entity
public class TextQuestion extends Question {

    private String wasd;

    public TextQuestion() {
        super();
        this.wasd = "wasd";
        this.setType("text");
    }

    public TextQuestion(String question, Survey survey) {
        super("text", question, survey);
        this.wasd = "asdf";
        this.setType("text");
    }

    public String getWasd() {
        return wasd;
    }

    public void setWasd(String wasd) {
        this.wasd = wasd;
    }
}
