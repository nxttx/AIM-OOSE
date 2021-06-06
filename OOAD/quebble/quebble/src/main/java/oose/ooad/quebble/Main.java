package src.main.java.oose.ooad.quebble;

import src.main.java.oose.ooad.quebble.DAO.MockData.MockUserData;
import src.main.java.oose.ooad.quebble.DAO.UserDAO;
import src.main.java.oose.ooad.quebble.service.Player;
import src.main.java.oose.ooad.quebble.strategy.ScoreBonusCalculate;
import src.main.java.oose.ooad.quebble.strategy.ScoreStandardCalculate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Main {

    private int stateMachineState = 0;
    private final int LOGIN = 0;
    private final int MENU = 1;
    private final int GAME = 2;
    private final int CREDITS_KOPEN = 3;

    private Player player;
    private UserDAO userDAO = new UserDAO();
    private int loginAttempts;

    public void handleGame() throws IOException {
        BufferedReader reader;
        try {
            System.out.println("We hebben "+playGame() + " voor je geselecteerd.");
        } catch (Exception exception) {
            System.out.println("Je hebt niet genoeg credits.");
            stateMachineState = MENU;
            return;
        }
        for(int i = 0; i < 8; i++) {
            System.out.println(getQuestion());
            reader = new BufferedReader(new InputStreamReader(System.in));

            String antwoord = reader.readLine();
            if (giveAnswer(antwoord)) {
                System.out.println("Dat klopt helemaal!");
            } else {
                System.out.println("Jammer dat is niet correct.");
            }
        }
        System.out.println("Je hebt alle vragen beantwoord! \r\nMaak nu met de volgende letters een zo lang mogelijk woord.");
        System.out.println(getCharacters());
        String antwoord;
        boolean wordIsCorrect = false;
        do{
            reader = new BufferedReader(new InputStreamReader(System.in));

            antwoord= reader.readLine();
            wordIsCorrect=guessWord(antwoord);
            if(wordIsCorrect){
                System.out.println("Goed bedacht! Nu gaan we je score berekenen.");
            }else{
                System.out.println("Jammer. Dat woord kennen wij niet met deze letters. Probeer het opnieuw.");
            }
        }while(!wordIsCorrect);
        player.setStrategy(new ScoreStandardCalculate());
        getScore();

        stateMachineState = MENU;
    }

    public boolean guessWord(String word) {
        return player.guessWord(word);
    }

    public String getCharacters(){
        String returnString= "";
        for(char i : player.getCharacters()){
            returnString += " "+ i + "," ;
        }
        // to remove last , from string
        returnString = returnString.substring(0, returnString.length() - 1);
        return returnString;
    }

    public String getQuestion(){
        return player.getQuestion();
    }

    public void buyCredits() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Hoeveel credits wil je kopen?");
        int creditAmount = parseInt(reader.readLine());

        System.out.println("je gaat " + creditAmount + " credits kopen.");

        player.buyCredits(creditAmount);
        System.out.println("Je hebt nu " + player.getCredits() + " credits.");
        stateMachineState = MENU;
    }

    public boolean giveAnswer(String answer) {
        return player.answerQuestion(answer);
    }

    public String playGame() throws Exception {
        return player.playGame();
    }

    public void logIn() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Login om te kunnen beginnnen.");
        System.out.println("Gebruikersnaam: ");
        String username = reader.readLine();

        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Wachtwoord: ");
        String password = reader.readLine();

        if (!userDAO.checkCredentials(username, password)) {
            System.out.println("Gegevens zijn niet correct.");
            loginAttempts++;

            if (loginAttempts < 3) {
                logIn();
                return;
            } else {
                System.out.println("Te veel incorrecte pogingen.");
                System.exit(-1);
            }
        }

        player = MockUserData.player1;
        System.out.println("ingelogged!");
        System.out.println("Welkom " + player.getUsername());
        stateMachineState = MENU;
    }

    public void showMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Wat wil je gaan doen?");
        System.out.println("1: Speel quiz");
        System.out.println("2: Koop credits");
        System.out.println("3: Stop");

        String optie = reader.readLine();

        switch (optie) {
            case "1":
                stateMachineState = GAME;
                break;
            case "2":
                stateMachineState = CREDITS_KOPEN;
                break;
            case "3":
                System.exit(-1);
            default:
                showMenu();
        }

    }

    public void getScore() {

        if(player.getCredits() > 100)
        {
            player.setStrategy(new ScoreStandardCalculate());
        } else {
            player.setStrategy(new ScoreBonusCalculate());
        }

        int score = player.getScore();
        System.out.println("Je score is " + score);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.loop();
    }

    public void loop(){
        while(true){
            switch(stateMachineState){
                case LOGIN:
                    try {
                        logIn();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case MENU:
                    try {
                        showMenu();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case GAME:
                    try {
                        handleGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case CREDITS_KOPEN:
                    try {
                        buyCredits();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    }

}
