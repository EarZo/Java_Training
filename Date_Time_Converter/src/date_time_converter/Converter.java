/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date_time_converter;

/**
 *
 * @author Eranda
 */
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Scanner;

public class Converter {

    public void convert() {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter the year: ");
        int year = scn.nextInt();

        System.out.print("\nEnter the month: ");
        int month = scn.nextInt();

        System.out.print("\nEnter the date: ");
        int date = scn.nextInt();

        System.out.print("\nEnter the hour: ");
        int hour = scn.nextInt();

        System.out.print("\nEnter the minutes: ");
        int minutes = scn.nextInt();

        System.out.print("\nEnter the seconds: ");
        int seconds = scn.nextInt();
        
        LocalDateTime ldt = LocalDateTime.of(year, month, date, hour, minutes, seconds);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());

        System.out.println("\n\nThe date & time you entered is: " + ldt);
        System.out.println("Your current time zone is: " + ZoneId.systemDefault());
        System.out.println("The current Coordinated Universal Time (UTC) for the date & time you entered is: " + zdt.withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println("The current Pacific Standard Time (PST) for the date & time you entered is: " + zdt.withZoneSameInstant(ZoneId.of("US/Pacific")) + "\n");
    }
}
