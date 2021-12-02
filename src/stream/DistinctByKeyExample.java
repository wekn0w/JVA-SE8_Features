package stream;

/*
    Demonstrate how to create a stateful Predicate
*/

import stream.model.Dog;

import java.util.ArrayList;
import java.util.List;

public class DistinctByKeyExample {

    // a stateful predicate


    public static void main(String[] args) {

        Dog white = new Dog("White", 2);
        Dog fluffy1 = new Dog("Fluffy", 4);
        Dog spot = new Dog("Spot", 2);
        Dog fluffy2 = new Dog("Fluffy", 1);

        List<Dog> list = new ArrayList<>();
        list.add(white);
        list.add(fluffy1);
        list.add(spot);
        list.add(fluffy2);

        list.stream()
                .distinct()//hashcode&equals are overriden
                .forEach(System.out::println);

    }
}
