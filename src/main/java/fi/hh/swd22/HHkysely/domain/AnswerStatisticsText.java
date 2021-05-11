package fi.hh.swd22.HHkysely.domain;

public class AnswerStatisticsText extends AnswerStatistics {
    private String answer;

    public AnswerStatisticsText(Long id, String answer) {
        super(id);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
