package com.eranda.vault;

import com.eranda.DispenseHandler;

public class Dispenser extends DispenseHandler {

    @Override
    public String dispense(Note note) {
        if(note.getAmount()%100 > 0){
            return "Invalid amount! Please enter the amount in multiples of hundreds!";
        }

        return successor.dispense(note);
    }
}
