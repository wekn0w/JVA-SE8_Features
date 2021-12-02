package stream;

import org.junit.Test;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

public class GroupByExample {

    public static Stream<String> getStream() {
        return Stream.of("John", "Paul", "George", "Ringo", "Paul", "John", "Paul");
    }

    @Test
    public void testGroupBy() {

        // 1) Create a map (string value->amount of these values)
        // use Function.identity() as a classifier function for groupingBy
        Map<String, Long> collect = getStream().collect(
                groupingBy(Function.identity(),
                        counting()));

        System.out.println(collect);
        assertEquals(collect.toString(), "{George=1, John=2, Ringo=1, Paul=3}");

        // 2) Create a map (string length->set of strings of this length)
        Map<Integer, Set<String>> collect2 = getStream().collect(
                groupingBy(String::length, toSet()));

        System.out.println(collect2);
        assertEquals(collect2.toString(), "{4=[John, Paul], 5=[Ringo], 6=[George]}");

        // 3) Create a map (string length->string of comma separated values of this length)
        Map<Integer, String> collect3 = getStream().distinct().collect(
                groupingBy(String::length, joining(",")));

        System.out.println(collect3);
        assertEquals(collect3.toString(), "{4=John,Paul, 5=Ringo, 6=George}");

        //***************************************************
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        // 1) Group locales by the country
        Map<String, List<Locale>> countryToLocales = locales.collect(
                Collectors.groupingBy(Locale::getCountry));

        countryToLocales.forEach((k, v) -> System.out.println(k + " = " + v));

        locales = Stream.of(Locale.getAvailableLocales());
        // 2) Group locales by country and calculate how many locales are there for each country
        Map<String, Long> countryToLocaleCounts = locales.collect(
                Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
        countryToLocaleCounts.forEach((k, v) -> System.out.println(k + " = " + v));
    }

}
