package aim.oose.dea;

public class Calculator {
    private CustomCalculation customCalculation;

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
            throw new IllegalArgumentException("Can't divide with 0");
        }
        return x / y;
    }

    public void setCustomCalculation(CustomCalculation customCalculation){
        this.customCalculation = customCalculation;
    }

    public int performCustomCalculation(int x, int y){
        if(customCalculation != null){
            return customCalculation.calculate(x,y);
        }
        return -1;
    }

    public static void main(String[] args){
        Calculator calculator = new Calculator();

        ////old fashion

//        MyCustomCalculation myCustomCalculation = new MyCustomCalculation();
//        calculator.setCustomCalculation(myCustomCalculation);
//
//        System.out.println(calculator.performCustomCalculation(10,5));

        ////new LAMBDA

        calculator.setCustomCalculation(
                (x, y)->{return x-y;}
        );
        System.out.println(calculator.performCustomCalculation(50, 25));
    }

}
