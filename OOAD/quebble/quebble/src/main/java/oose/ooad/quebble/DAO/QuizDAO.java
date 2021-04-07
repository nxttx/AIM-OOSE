package src.main.java.oose.ooad.quebble.DAO;

import src.main.java.oose.ooad.quebble.DAO.MockData.MockQuizData;
import src.main.java.oose.ooad.quebble.service.Quiz;

public class QuizDAO {

    public Quiz getQuiz() {
        return MockQuizData.getRandomQuiz();
    }

}
