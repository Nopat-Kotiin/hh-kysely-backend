package fi.hh.swd22.HHkysely.domain;

import java.util.Map;

public class AnswerStatisticsChoice extends AnswerStatistics {

    private Map<String, Integer> selections;

    public AnswerStatisticsChoice(long id, String question, String type) {
        super(id, question, type);
    }

    public Map<String, Integer> getSelections() {
        return selections;
    }

    public void setSelections(Map<String, Integer> selections) {
        this.selections = selections;
    }

}
