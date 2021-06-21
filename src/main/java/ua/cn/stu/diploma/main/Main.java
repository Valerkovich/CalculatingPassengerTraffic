package ua.cn.stu.diploma.main;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        InitDB.init();
        CountPassengers.countPassengersEveryStop();
        CountPassengers.averageCountPassenger();
        CountPassengers.countPassengerRatioWithPrivilege();

        System.exit(0);
    }
}
