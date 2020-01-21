/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateTime_Converter;

/**
 *
 * @author Eranda
 */
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Converter {

    Scanner scanner = new Scanner(System.in);

    public void convert() {

        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        System.out.print("Enter the month: ");
        int month = scanner.nextInt();

        System.out.print("Enter the date: ");
        int date = scanner.nextInt();

        System.out.print("Enter the hour: ");
        int hour = scanner.nextInt();

        System.out.print("Enter the minutes: ");
        int minutes = scanner.nextInt();

        System.out.print("Enter the seconds: ");
        int seconds = scanner.nextInt();

        LocalDateTime localDateTime = LocalDateTime.of(year, month, date, hour, minutes, seconds);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        System.out.println("\nThe time you entered: " + localDateTime);
        System.out.println("Your current time zone: " + ZoneId.systemDefault());
        System.out.println("Coordinated Universal Time (UTC) for the time you entered: " + zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println("Pacific Standard Time (PST) for the time you entered: " + zonedDateTime.withZoneSameInstant(ZoneId.of("US/Pacific")) + "\n");
    }
}
