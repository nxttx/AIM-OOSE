package src.main.java.oose.ooad.quebble.service;

import src.main.java.oose.ooad.quebble.adapter.IWoordenboekAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class Quiz {

    private Question[] questions;
    private String name;
    private IWoordenboekAdapter woordenboekAdapter;
    private long startTime;

    public void playGame() {

    }

    public void setStartTime()
    {
        this.startTime = java.lang.System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public String getQuestion(int id) {
        String returnString= questions[id].getQuestion();
        if(questions[id].getAwnserOptions().length>1){
            for(String i :questions[id].getAwnserOptions()){
                returnString+="\r\n    "+i;
            }
        }
        return returnString ;
    }

    public boolean checkAnswer(String answer, int questionId){
        return (answer.toLowerCase(Locale.ROOT).equals(questions[questionId].getCorrectAnswer().toLowerCase(Locale.ROOT)));
    }

    public boolean checkWord(String word) {
        return woordenboekAdapter.checkWord(word);
    }

    public ArrayList<Character> getCharacters(ArrayList<Integer> questionID) {

        ArrayList<Character> returnObject = new ArrayList<>();
        for(int i :questionID){
            returnObject.add(questions[i].getCharacter());
        }
        return returnObject ;
    }

    public String getName() {
        return name;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public void setName(String name) {
        this.name = name;
    }


    //inject
    public void setWoordenboekAdapter(IWoordenboekAdapter woordenboekAdapter) {
        this.woordenboekAdapter = woordenboekAdapter;
    }

}
