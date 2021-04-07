package src.main.java.oose.ooad.quebble.DAO.MockData;

import src.main.java.oose.ooad.quebble.service.Question;
import src.main.java.oose.ooad.quebble.service.Quiz;

import java.util.Random;

public class MockQuizData {
    public static Question question1= new Question("Welke status heeft Aruba?", 'a', "Land in het koningkrijk der Nederlanden", new String[]{"Land in het koningkrijk der Nederlanden", "Bijzondere gemeente van Nederland"});
    public static Question question2= new Question("De gemeente neemt maatregelen tegen wateroverlast door hoosbuien. Wat helpt niet", 'b', "Meer busbanen aanleggen", new String[]{"De riolering verbeteren", "Meer groen aanleggen", "Sloten verbreden", "Meer busbanen aanleggen"});
    public static Question question3= new Question("Nederland ligt deels deels onder zee niveau.", 'r', "Waar", new String[]{"Waar", "Niet waar"});
    public static Question question4= new Question("Wat is 1+1", 'a', "2", new String[]{});
    public static Question question5= new Question("De gemeente neemt maatregelen tegen wateroverlast door hoosbuien. Wat helpt niet", 'b', "Meer busbanen aanleggen", new String[]{"De riolering verbeteren", "Meer groen aanleggen", "Sloten verbreden", "Meer busbanen aanleggen"});
    public static Question question6= new Question("Nederland ligt deels deels onder zee niveau.", 'r', "Waar", new String[]{});
    public static Question question7= new Question("Welke status heeft Aruba?", 'a', "Land in het koningkrijk der Nederlanden", new String[]{"Land in het koningkrijk der Nederlanden", "Bijzondere gemeente van Nederland"});
    public static Question question8= new Question("De gemeente neemt maatregelen tegen wateroverlast door hoosbuien. Wat helpt niet", 'b', "Meer busbanen aanleggen", new String[]{"De riolering verbeteren", "Meer groen aanleggen", "Sloten verbreden", "Meer busbanen aanleggen"});

    private static Question[] questionArray1 = {question1, question2, question3, question4, question5, question6, question7, question8};

    public static Quiz quiz1 = buildQuiz("Topografie",questionArray1);

    private static int quizLength = 1;
    public static Quiz[] quizzes = {quiz1};


    private static Quiz buildQuiz(String name, Question[] questions) {
        Quiz newQuiz = new Quiz();
        newQuiz.setQuestions(questions);
        newQuiz.setName(name);
        return newQuiz;
    }

    public static Quiz getRandomQuiz(){
        Random rand = new Random();
        return quizzes[rand.nextInt(quizLength)];
    }


}
