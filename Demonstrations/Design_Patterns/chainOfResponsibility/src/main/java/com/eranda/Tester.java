package com.eranda;

import com.eranda.vault.*;

import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {

        Dispenser dispenser = new Dispenser();
        FiveThousandDispenser fiveThousandDispenser = new FiveThousandDispenser();
        ThousandDispenser thousandDispenser = new ThousandDispenser();
        HundredDispenser hundredDispenser = new HundredDispenser();

        dispenser.setSuccessor(fiveThousandDispenser);
        fiveThousandDispenser.setSuccessor(thousandDispenser);
        thousandDispenser.setSuccessor(hundredDispenser);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the amount in multiples of hundreds: ");
        double amount = scanner.nextDouble();

        Note note = new Note(amount);
        System.out.println(dispenser.dispense(note));
    }

}
