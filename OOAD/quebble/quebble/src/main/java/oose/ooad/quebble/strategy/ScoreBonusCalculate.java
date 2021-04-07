package src.main.java.oose.ooad.quebble.strategy;

public class ScoreBonusCalculate implements IScoreStrategy {

    public long calculate(int correctAnswers, long totalTime, int wordLength) {
        return ((long) correctAnswers * wordLength / totalTime) * 2;
    }

}
