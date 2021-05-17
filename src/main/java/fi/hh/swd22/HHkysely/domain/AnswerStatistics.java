package fi.hh.swd22.HHkysely.domain;

public abstract class AnswerStatistics {
    private long questionId;
    private String question;


    public AnswerStatistics(long id, String question) {
        this.questionId = id;
        this.question = question;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
}
