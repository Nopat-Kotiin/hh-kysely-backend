package fi.hh.swd22.HHkysely.domain;

import java.util.List;

public class AnswerStatisticsText extends AnswerStatistics {
    private List<String> answers;

    public AnswerStatisticsText(Long id, List<String> answers) {
        super(id);
        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    
}
