package aim.oose.dea;

public class Calculator {
    public float multiply(float x, float y) {
        return x * y;
    }

    public float add(float x, float y) {
        return x + y;
    }

    public float subtract(float x, float y) {
        return x - y;
    }

    public float divide(float x, float y) {
        if(y <=0.0){
            //todo: exceptions
            System.out.println("Can't divide with 0");
            return x;
        }
        return x / y;
    }
}
