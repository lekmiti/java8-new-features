package com.lekmiti.java8.dateTimeApi;


import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class DateTimeApi {

    @Test
    public void localDate() {
        // when
        LocalDate today = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2016, 01, 05);
        LocalDate localDate2 = LocalDate.parse("2016-01-05");

        // then
        assertTrue(localDate1.equals(localDate2));
        assertTrue(localDate1.getYear() == 2016);
        assertTrue(localDate1.getMonth() == Month.JANUARY);
        assertTrue(localDate1.getDayOfMonth() == 5);
        assertTrue(localDate1.getDayOfWeek() == DayOfWeek.TUESDAY);
        assertTrue(localDate1.getDayOfYear() == 5);
        assertTrue(localDate1.plusDays(2).equals(LocalDate.of(2016, 01, 07)));
        assertTrue(localDate1.isBefore(LocalDate.of(2016, 01, 07)));
        assertTrue(localDate1.isAfter(LocalDate.of(2016, 01, 03)));

    }


    @Test
    public void localTime() {

        // when
        LocalTime sevenOclock = LocalTime.parse("07:00");
        LocalTime eightOclock = LocalTime.of(8, 00);
        LocalTime sevenThirty = LocalTime.parse("07:00").plus(30, ChronoUnit.MINUTES);
        LocalTime tenOclock = eightOclock.plus(2, ChronoUnit.HOURS);
        long sixtyMinutes = ChronoUnit.MINUTES.between(sevenOclock, eightOclock);
        long twoHours = ChronoUnit.HOURS.between(sevenOclock, eightOclock);

        //then
        assertTrue(sevenOclock.toString().equals("07:00"));
        assertTrue(eightOclock.toString().equals("08:00"));
        assertTrue(sevenThirty.toString().equals("07:30"));
        assertTrue(tenOclock.toString().equals("10:00"));
        assertTrue(sixtyMinutes == 60);
        assertTrue(twoHours == 1);


    }

    @Test
    public void localDateTime() {


        // When
        LocalDateTime localDateTime1 = LocalDateTime.of(2017, Month.AUGUST, 10, 8, 40);
        LocalDateTime localDateTime2 = LocalDateTime.parse("2015-02-20T06:30:00");
        LocalDateTime beginningOfDay = LocalDate.parse("2016-01-05").atStartOfDay();
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-05")
                .with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfYear = LocalDate.parse("2016-06-05")
                .with(TemporalAdjusters.lastDayOfYear());

        // Then
        assertTrue(localDateTime1.toString().equals("2017-08-10T08:40"));
        assertTrue(localDateTime2.toString().equals("2015-02-20T06:30"));
        assertTrue(localDateTime1.plusDays(2).toString().equals("2017-08-12T08:40"));
        assertTrue(localDateTime1.minusHours(3).toString().equals("2017-08-10T05:40"));
        assertTrue(beginningOfDay.toString().equals("2016-01-05T00:00"));
        assertTrue(beginningOfDay.toString().equals("2016-01-05T00:00"));
        assertTrue(firstDayOfMonth.toString().equals("2016-06-01"));
        assertTrue(lastDayOfYear.toString().equals("2016-12-31"));
    }

    @Test
    public void ZonedDateTime() {
        ZoneId cuibaZoneId = ZoneId.of("America/Cuiaba");
        ZoneId nairobiZoneId = ZoneId.of("Africa/Nairobi");

        LocalDateTime localDateTime = LocalDateTime.of(2017, Month.AUGUST, 10, 8, 40);
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();

        ZonedDateTime nairobiDateTime = ZonedDateTime.of(localDateTime, nairobiZoneId);
        ZonedDateTime cuibaDateTime = ZonedDateTime.of(localDateTime, cuibaZoneId);
        System.out.println(nairobiDateTime);


        assertTrue(allZoneIds.contains("Europe/London"));
        assertTrue(nairobiDateTime.toString().equals("2017-08-10T08:40+03:00[Africa/Nairobi]"));
        assertTrue(cuibaDateTime.toString().equals("2017-08-10T08:40-04:00[America/Cuiaba]"));
    }

}
