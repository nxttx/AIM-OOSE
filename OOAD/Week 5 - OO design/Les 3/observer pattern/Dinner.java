public class Dinner {
    public static void main(String[] args) {
        Parent mom = new Parent("Mary");
        Child tim = new Child("Tim", 15);
        Child ruby = new Child("Ruby", 13);
        Dog pluto = new Dog("Pluto");
        mom.attach(tim);
        mom.attach(ruby);
        mom.attach(pluto);
        mom.setDinnerReady();
    }
}