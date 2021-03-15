public class Child implements Observer {
    private String name;
    private int age;

    public Child(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void update() {
        System.out.println(name + " says: I'll be there in a minute");
    }
}
