package fi.hh.swd22.HHkysely.domain;

public class AnswerStatisticsChoice extends AnswerStatistics {

    private int choice;
    private long amount;

    public AnswerStatisticsChoice(long id, int choice, long amount) {
        super(id);
        this.choice = choice;
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

}
