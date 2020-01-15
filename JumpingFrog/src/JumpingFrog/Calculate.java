/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JumpingFrog;

import java.util.Scanner;

/**
 *
 * @author Eranda
 */
public class Calculate {

    Scanner scanner = new Scanner(System.in);

    public void calculateTime() {

        int distance = 0;
        int count = 0;

        try {
            System.out.print("\nEnter the distance the frog jumped in meters: ");
            distance = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input! Please try again.");
            calculateTime();
        } finally {

            if (distance > 9) {
                count = distance / 9;
                distance = distance % 9;
            }

            if (distance <= 0) {
                System.out.println("Invalid input! Please try again.");
                calculateTime();
            } else if (distance >= 1 && distance <= 5) {
                System.out.println("The frog will take " + ((8 * count) + 1) + " seconds to cover the given distance");
            } else if (distance <= 8) {
                System.out.println("The frog will take " + ((8 * count) + 3) + " seconds to cover the given distance");
            } else {
                System.out.println("The frog will take " + ((8 * count) + 8) + " seconds to cover the given distance");
            }
        }
    }

    public void calculateDistance() {

        int time = 0;
        int count = 0;

        try {
            System.out.print("\nEnter the time the frog took to jump in seconds: ");
            time = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input! Please try again.");
            calculateDistance();
        } finally {

            if (time > 8) {
                count = time / 8;
                time = time % 8;
            }

            if (time <= 0) {
                System.out.println("Invalid input! Please try again.");
                calculateDistance();
            } else if (time == 1) {
                System.out.println("The frog will jump " + ((9 * count) + 5) + " meters in the given time");
            } else if (time <= 3) {
                System.out.println("The frog will jump " + ((9 * count) + 8) + " meters in the given time");
            } else {
                System.out.println("The frog will jump " + ((9 * count) + 9) + " meters in the given time");
            }
        }
    }
}
