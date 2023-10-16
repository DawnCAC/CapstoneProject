package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLOutput;
import java.time.*;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Locale;

/**
 * Project: C195-Scheduler
 * Package: package helper;
 * User: Dawn Fitch - Student ID: 001346922
 * Created with IntelliJ
 * This class holds the time conversions for the project
 */
public class DateTime {
    /**
     * Gets the list of time to fill apptTime in the AddModAppt class
     * @return
     */
    public static ObservableList getApptTimes() {
        ObservableList apptTimes = FXCollections.observableArrayList();
        LocalTime myLT = LocalTime.of(00,00);
        int i = 0;
        while (i < 24){
            apptTimes.add(myLT);
            myLT = myLT.plusHours(1);
            i++;

        }
        return apptTimes;
    }

    /**
     * Gets the date for the start of the week
     * @return
     */
    public static LocalDate weekStartDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate start = null;
        if (currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            start = currentDate;
        }else{
            start = currentDate.minusDays(currentDate.getDayOfWeek().getValue());
        }
        return start;
    }

    /**
     * Gets the date for the start of the month
     * @return
     */
    public static LocalDate monthStartDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate start = currentDate.withDayOfMonth(1);
        return start;
    }

    /**
     * Compares the start and end dates of an appointment with business hours
     * @param apptStart
     * @param apptEnd
     * @return
     */
    public static boolean outsideHours(LocalDateTime apptStart, LocalDateTime apptEnd){
        boolean outsideHours = false;
        ZonedDateTime apptStartEst = apptStart.atZone(ZoneId.of("US/Eastern"));
        LocalDate apptDate = apptStartEst.toLocalDate();
        LocalTime time = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(22,0);
        LocalDateTime start = LocalDateTime.of(apptDate, time);
        LocalDateTime end = LocalDateTime.of(apptDate, endTime);
        outsideHours = apptStart.isBefore(start) || apptEnd.isAfter(end);
        return outsideHours;
    }

}
