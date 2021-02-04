package HAN.AIM.OOSE.DEA;

import java.util.Locale;

public class App{
    public static void main(String[] args) {
        action();
    }

    private static void action() {
        App app = new App();
        try{
            app.doSomething("corona");
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
        }finally {
            System.out.println("Dit wordt altijd uitgevoerd");
        }
    }

    public void doSomething(String name) throws DiscriminationException, DiseaseException {
        if(name.equals("Zwarte Piet")){
            throw new DiscriminationException("Dit kan echt niet meer");
        }
        if(name.toUpperCase().equals("CORONA")){
            throw new DiseaseException("Hier willen we graag vanaf");
        }
        System.out.println("Mooie naam");
    }
}
