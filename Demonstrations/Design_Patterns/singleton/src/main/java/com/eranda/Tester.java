package com.eranda;

public class Tester {

    public static void main(String[] args) {
        Printer printer = Printer.getPrinter();
        System.out.println(printer);
        printer.print("Print this line!");
    }

}
