package src.main.java.oose.ooad.quebble.strategy;

public interface IScoreStrategy {

    public long calculate(int correctAnswers, long totalTime, int wordLength);

}
