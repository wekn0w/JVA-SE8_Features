package datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class LocalDateMoreExample {

    public static void main(String[] args) {

        /* 1) print current time in London in HH:mm dd/MM/yyyy format;
              ZoneId.of("Europe/London") and then ZonedDateTime to retrieve London time;
              use format() method and DateTimeFormatter.ofPattern()
        */
        ZoneId id = ZoneId.of("Europe/London");
        ZonedDateTime dateTime = ZonedDateTime.now(id);
        String londonTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
        System.out.println(londonTime);

        // 2) calculate Java's age in days, months and years
        LocalDate now = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(1995, 05, 23);
        Period p = Period.between(dateOfBirth, now);
        System.out.printf("Java is %d years, %d months, and %d days old %n",
                p.getYears(), p.getMonths(), p.getDays());

        // 3) calculate and show the date of Java's next birthday in the dd.mm.yyyy format
        System.out.println(dateOfBirth.plusYears(p.getYears() + 1)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // 4) calculate Java's age in seconds and in hours
        LocalDateTime moment = LocalDateTime.now();
        LocalDateTime timeOfBirth = LocalDateTime.of(1995, 05, 23, 8, 30);
        Duration d = Duration.between(timeOfBirth, moment);
        System.out.println(d.getSeconds());
        System.out.println(d.toHours());

        // 5) show day of the week of Java's next birthday
        LocalDate nextBirthday = dateOfBirth.plusYears(p.getYears() + 1);
        System.out.println(nextBirthday.getDayOfWeek());

        // 6) check if Java's birthday date this year is ahead or behind
        System.out.println("Java's birthday date is "
                + (nextBirthday.isAfter(LocalDate.now()) ? "ahead" : "behind"));

        /* 7) calculate time difference between local time and New York time;
              use TimeZone.getRawOffset() to calculate difference in milliseconds;
              use TimeZone.getTimeZone("America/New_York") to get New York timezone;
              use TimeZone().getDefault() to get local timezone;
              use TimeUnit.MILLISECONDS.toHours() to convert it to hours
        */
        int offsetNow = TimeZone.getTimeZone("America/New_York").getOffset(Instant.now().toEpochMilli());
        System.out.println(TimeUnit.MILLISECONDS.toHours(offsetNow));
        int rawOffset = TimeZone.getTimeZone("America/New_York").getRawOffset() - TimeZone.getDefault().getRawOffset();
        System.out.println(TimeUnit.MILLISECONDS.toHours(rawOffset));

        /* 8) suppose we have New York time "02.04.2016 22:11";
              calculate local time and print it in the same format
              use ZonedDateTime.withZoneSameInstant() method and ZoneOffset.systemDefault()
              to get local ZoneId
        */
        String time = "02.04.2016 22:11";

        LocalDateTime parsedTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        // now compute New York time
        ZonedDateTime nyTime = parsedTime.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime localTime = nyTime.withZoneSameInstant(ZoneOffset.systemDefault());
        System.out.println(localTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    }
}
