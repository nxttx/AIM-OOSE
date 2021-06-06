package src.main.java.oose.ooad.quebble.service;

import src.main.java.oose.ooad.quebble.DAO.QuizDAO;
import src.main.java.oose.ooad.quebble.adapter.IBetaalServiceAdapter;
import src.main.java.oose.ooad.quebble.adapter.OnlineBetaalServiceAdapter;
import src.main.java.oose.ooad.quebble.adapter.RandomWoordenboekAdapter;
import src.main.java.oose.ooad.quebble.strategy.IScoreStrategy;

import java.util.ArrayList;

public class Player {

    private String username;
    private String password;
    private int credits;
    private IBetaalServiceAdapter betaalServiceAdapter = new OnlineBetaalServiceAdapter();
    private Quiz quiz;
    private IScoreStrategy scoreStrategy;
    private QuizDAO quizDAO = new QuizDAO();
    private ArrayList<Boolean> answeredQuestions = new ArrayList<>();
    private int guessedWordLength;

    public void setGuessedWordLength(int guessedWordLength) {
        this.guessedWordLength = guessedWordLength;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setStrategy(IScoreStrategy scoreStrategy) {
        this.scoreStrategy = scoreStrategy;
    }

    public void buyCredits(int amount) {
        boolean status = betaalServiceAdapter.processTransaction(amount);

        if (status) {
            setCredits(getCredits() + amount);
        }
    }

    public boolean guessWord(String word) {
        for(int i = 0; i< word.length(); i++){
            if(!getCharacters().contains(word.charAt(i))){
                return false;
            }
        }
        setGuessedWordLength(word.length());
        quiz.setWoordenboekAdapter(new RandomWoordenboekAdapter());
        return quiz.checkWord(word);
    }

    public int getScore() {
        int correctAnswersCount = getCorrectAnswersCount();
        long duration = (java.lang.System.currentTimeMillis() - quiz.getStartTime()) / 1000;

        scoreStrategy.calculate(correctAnswersCount, duration, guessedWordLength);

        return correctAnswersCount;
    }

    public int getCorrectAnswersCount() {
        int correctAnswersCount = 0;
        for (int i = 0; i < answeredQuestions.size(); i++) {
            if (answeredQuestions.get(i)) {
                correctAnswersCount++;
            }
        }

        return correctAnswersCount;
    }

    public boolean answerQuestion(String answer) {
        boolean isCorrect = quiz.checkAnswer(answer, answeredQuestions.size());
        answeredQuestions.add(isCorrect);
        return isCorrect;

    }

    public ArrayList<Character> getCharacters() {
        ArrayList<Integer> correctAnswersIds = new ArrayList<>();
        for(int i = 0; i< answeredQuestions.size(); i++){
            if(answeredQuestions.get(i)){
                correctAnswersIds.add(i);
            }
        }

        return quiz.getCharacters(correctAnswersIds);
    }

    public String playGame() throws Exception {
        if(this.credits <20){
            throw new Exception("Not enough credits");
        }
        setCredits(getCredits() - 20);
        quiz = quizDAO.getQuiz();
        quiz.setStartTime();
        return quiz.getName();
    }

    public String getQuestion() {
        return quiz.getQuestion(answeredQuestions.size());
    }

}
