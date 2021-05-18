package fi.hh.swd22.HHkysely.domain;

public abstract class AnswerStatistics {
    private long questionId;
    private String question;
    private String type;


    public AnswerStatistics(long id, String question, String type) {
        this.questionId = id;
        this.question = question;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
