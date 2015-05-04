package tools;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Roman on 04.05.2015.
 */
public class Statistic {
    public static <T> T selectRandomByScore(List<T> collection, Function<T, Integer> calc) {
        if (collection.size() == 0) {
            throw new RuntimeException("Collection is empty");
        }
        if (collection.size() == 1) {
            return collection.get(0);
        }
        Stream<T> stream = collection.stream();
        long totalScore = stream.mapToLong(calc::apply).sum();
        long randomNumber = (long) (Math.random() * totalScore);
        System.out.println(totalScore);
        System.out.println(randomNumber);
        for (T t : collection) {
            long score = calc.apply(t);
            if (randomNumber < score) {
                return t;
            } else {
                randomNumber -= score;
            }
        }
        return collection.get(0);
    }
}
