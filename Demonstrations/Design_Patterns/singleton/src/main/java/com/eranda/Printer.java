package com.eranda;

public class Printer {

    private static volatile Printer printer;

    private Printer() {
        if (printer != null) {
            throw new RuntimeException("Please use getPrinter() method!");
        }
    }

    public static Printer getPrinter() {
        if (printer == null) {
            synchronized (Printer.class) {
                if (printer == null) {
                    printer = new Printer();
                }
            }
        }

        return printer;
    }

    public void print(String printLine) {
        synchronized (Printer.class) {
            System.out.println(printLine);
        }
    }
}
