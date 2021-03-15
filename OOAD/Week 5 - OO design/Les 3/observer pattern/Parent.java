public class Parent extends Subject {
    private String name;
    boolean dinnerReady = false;

    public Parent(String name) {
        this.name = name;
    }

    public void setDinnerReady() {
        dinnerReady = true;
        System.out.println(name + " says: Dinner's ready");
        this.notifyObservers();
    }
}