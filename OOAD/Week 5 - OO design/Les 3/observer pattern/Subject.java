import java.util.ArrayList;

public abstract class Subject {
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer obs) {
        observers.add(obs);
    };

    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }
}