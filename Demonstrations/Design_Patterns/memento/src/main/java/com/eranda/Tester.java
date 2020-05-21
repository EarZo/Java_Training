package com.eranda;

public class Tester {

    public static void main(String[] args) {

        CareTaker careTaker = new CareTaker();
        Notepad notepad = new Notepad();

        notepad.setLastState("Copy 1");
        careTaker.save(notepad);
        System.out.println("Saving to undo list...");
        System.out.println(notepad);

        notepad.setLastState("Copy 2");
        careTaker.save(notepad);
        System.out.println("Saving to undo list...");
        System.out.println(notepad);

        notepad.setLastState("Copy 3");
        System.out.println("\n\n" + notepad.getCopiedText() +" is currently in use!\n\n");

        careTaker.revert(notepad);
        System.out.println("Undo...");
        System.out.println(notepad);

        careTaker.revert(notepad);
        System.out.println("Undo...");
        System.out.println(notepad);
        System.out.println("\n\n" + notepad.getCopiedText() +" is currently in use!\n\n");

    }

}
