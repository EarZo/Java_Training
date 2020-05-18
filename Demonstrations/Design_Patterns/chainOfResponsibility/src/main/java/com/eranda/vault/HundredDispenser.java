package com.eranda.vault;

import com.eranda.DispenseHandler;

public class HundredDispenser extends DispenseHandler {

    @Override
    public String dispense(Note note) {
        return "Dispense " + (int) note.getAmount()/100 + " LKR100 notes!";
    }
}
