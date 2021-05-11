package fi.hh.swd22.HHkysely.domain;

public abstract class AnswerStatistics {
    private long questionId;


    public AnswerStatistics(long id) {
        this.questionId = id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
    
}
