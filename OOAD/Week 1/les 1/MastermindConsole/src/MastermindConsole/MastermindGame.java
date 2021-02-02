package MastermindConsole;

import java.util.Random;

public class MastermindGame {
    private String secretCode ="";
    private int usedTurns = 0;
    private boolean isWon= false;
    private String guessString= "";

    MastermindGame(){
        //silence is golden
    }

    public void generateSecretCode(){
        String[] colors = {"R", "B", "G","Y"};
        String code= "";

        for(int i =0; i<4; i++){
            int value = new Random().nextInt(4); // max is 4
            code += colors[value];
        }
//        System.out.println(code);

        this.secretCode=code;

    }

    public void checkCombination(String guessString) {
        this.guessString = guessString;
        if(guessString.equals(this.secretCode)){
            isWon=true;
        }
        this.usedTurns++;

    }

    public String getHint() {
        int perfectPlacement = 0;
        int goodColor = 0;
        String newGuessString= "";
        String newSecretCode= "";
        for(int i= 0; i<4 ; i++){
            if(guessString.charAt(i) == secretCode.charAt(i)){
                perfectPlacement++;
                newGuessString+="_";
                newSecretCode+="+";
            }else{
                newGuessString+=guessString.charAt(i);
                newSecretCode+= secretCode.charAt(i);
            }
        }
        System.out.println("Right colour right position = " + perfectPlacement);
        for(int guess= 0; guess<4 ; guess++) {
            for(int secret= 0; secret<4 ; secret++){
                if(newGuessString.charAt(guess) == newSecretCode.charAt(secret)){
                    goodColor++;
                    newGuessString= newGuessString.substring(0,guess)+'_'+newGuessString.substring(guess+1);
                    newSecretCode= newSecretCode.substring(0,secret)+'-'+newSecretCode.substring(secret+1);
                }
            }
        }
        return "Right colour wrong position = " + goodColor;
    }

    public boolean isWon() {
        return isWon;
    }

    public boolean maxTurnsUsed() {
        if(usedTurns<= 8){
            return false;
        }else{
            return true;
        }
    }

    public String getSecretCode() {
        return secretCode;
    }
}
