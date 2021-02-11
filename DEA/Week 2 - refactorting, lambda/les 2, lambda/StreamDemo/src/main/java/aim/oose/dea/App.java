package aim.oose.dea;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        ArrayList<Integer> grades = new ArrayList<>();
        grades.add(4);
        grades.add(7);
        grades.add(5);

        //Demo for print element
        grades.stream().forEach((grade) -> {
            System.out.println(grade);
        });


        System.out.println("---- filter demo----");
        // demo map+ filter
        grades.stream()
                .map(grade -> grade +1)
                .filter(grade -> grade>=6)
                .forEach(
                        grade -> System.out.println(grade)
                );


        System.out.println("---- change type demo----");
        //demo change type
        grades.stream()
                .map(grade -> {
                    return grade<6 ? "onvoldoende" : "voldoende";
                })
                .forEach(
                        grade -> System.out.println(grade)
                );


        System.out.println("---- collect demo----");
        //demo collect
        List<String> gradesAsString = grades.stream()
                .map(grade -> {
                    return grade<6 ? "onvoldoende" : "voldoende";
                })
                .collect(Collectors.toList());


        System.out.println("---- Int stream ----");
        //demo int stream
        OptionalDouble average = grades.stream()
                .mapToInt(grade->grade)
                .average();
        if(average.isPresent()){
            System.out.println(average.getAsDouble());
        }


        System.out.println("---- reduce ----");
        //demo reduce
        //KOMT NIET IN DE TOETS
        //calculate the sum of the grade
        int sum = grades.stream().reduce(0, (result, grade)-> grade+ result );
        System.out.println(sum);

    }

}
