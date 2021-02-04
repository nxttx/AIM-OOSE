package aim.oose.dea;

public class StringCalculator {

    public int add(String numbers) {
        if(numbers.startsWith("//")){ //if new method
            //check if there are multiple delimiters
            if(numbers.contains("][")){
                String[] temp= numbers.split("\\[|\\]|\n");
                return calcSum(temp[5], temp[1], temp[3]);
            }else{
                String[] temp= numbers.split("\\[|\\]|\n");
                return calcSum(temp[3], temp[1]);
            }

        }

        if (numbers.contains(",") || numbers.contains("\n")) { // if old method
            return calcSum(numbers, ",|\n");

        } else if(numbers.equals("")){
            return 0;
        }else{
            return Integer.parseInt(numbers);
        }

    }

    private int calcSum(String numbers, String delimiter ){
        String[] temp= numbers.split(delimiter);
        int total = 0;
        for (String s : temp) {
            if (Integer.parseInt(s) < 1000) {
                total += Integer.parseInt(s);
            }
        }
        return total;
    }

    private int calcSum(String numbers, String delimiter, String delimiter2 ){
        String[] temp= numbers.split(delimiter+"|"+delimiter2);
        int total = 0;
        for (String s : temp) {
            if (Integer.parseInt(s) < 1000) {
                total += Integer.parseInt(s);
            }
        }
        return total;
    }
}
