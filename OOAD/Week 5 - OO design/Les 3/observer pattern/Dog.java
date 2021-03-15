public class Dog implements Observer {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name + " thinks: I hope there will be something left");
    }
}