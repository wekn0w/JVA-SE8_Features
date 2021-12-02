package datetime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class LocalDateExample {

    public static void main(String[] args) {
        // current date & time
        LocalDateTime timePoint = LocalDateTime.now();
        // from values
        LocalDate.of(2012, Month.DECEMBER, 12);
        // mid-1970
        LocalDate.ofEpochDay(150);
        // train's departure time
        LocalTime.of(17, 18);
        // from a String
        LocalTime.parse("10:15:30");

        LocalDate theDate = timePoint.toLocalDate();
        Month month = timePoint.getMonth();
        int day = timePoint.getDayOfMonth();
        timePoint.getSecond();

        // initializes and returns a new Date&Time object
        LocalDateTime thePast = timePoint.withDayOfMonth(10)
                .withYear(2010);

        // can use direct manipulation methods, or pass a value and field pair
        LocalDateTime yetAnother = thePast.plusWeeks(3)
                .plus(3, ChronoUnit.WEEKS);

        LocalDate today = LocalDate.now();
        LocalDate payday = today.with(
                TemporalAdjusters.lastDayOfMonth()).minusDays(2);

        LocalDate dateOfBirth = LocalDate.of(2012, Month.MAY, 14);
        LocalDate firstBirthday = dateOfBirth.plusYears(1);

        // returns current time based on system clock 
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + LocalDateTime.now(clock));

        // returns time based on system clock's timezone 
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + LocalDateTime.now(defaultClock));

    }

}
