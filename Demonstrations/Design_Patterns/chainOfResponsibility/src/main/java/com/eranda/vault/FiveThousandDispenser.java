package com.eranda.vault;

import com.eranda.DispenseHandler;

public class FiveThousandDispenser extends DispenseHandler {

    @Override
    public String dispense(Note note) {
        double originalAmount = note.getAmount();
        double remainder = note.getAmount()%5000;

        if(remainder == 0){
            return "Dispense " + (int) note.getAmount()/5000 + " LKR5000 notes!";
        }else{
            note.setAmount(remainder);
            return "Dispense " + (int) originalAmount/5000 + " LKR5000 notes!\n" + successor.dispense(note);
        }
    }
}
