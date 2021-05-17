package fi.hh.swd22.HHkysely.domain;

import java.util.Map;

public class AnswerStatisticsChoice extends AnswerStatistics {

    private Map<Integer, Integer> selections;

    public AnswerStatisticsChoice(long id, String question) {
        super(id, question);
    }

    public Map<Integer, Integer> getSelections() {
        return selections;
    }

    public void setSelections(Map<Integer, Integer> selections) {
        this.selections = selections;
    }

}
