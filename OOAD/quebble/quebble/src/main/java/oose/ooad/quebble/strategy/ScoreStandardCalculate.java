package src.main.java.oose.ooad.quebble.strategy;

public class ScoreStandardCalculate implements IScoreStrategy {

    public long calculate(int correctAnswers, long totalTime, int wordLength) {

        return (long) correctAnswers * wordLength / totalTime;
    }

}
