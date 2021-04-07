package src.main.java.oose.ooad.quebble.service;

public class Question {

    private String question;
    private char character;
    private String correctAnswer;
    private String[] awnserOptions;

    public Question(String question, char character, String correctAnswer, String[] awnserOptions) {
        this.question = question;
        this.character = character;
        this.correctAnswer = correctAnswer;
        this.awnserOptions = awnserOptions;
    }

    public String getQuestion() {
        return question;
    }

    public char getCharacter() {
        return character;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getAwnserOptions() {
        return awnserOptions;
    }
}
