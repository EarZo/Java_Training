package com.eranda.vault;

import com.eranda.DispenseHandler;

public class ThousandDispenser extends DispenseHandler {

    @Override
    public String dispense(Note note) {
        double remainderFromPreviousDivision = note.getAmount();
        double remainder = note.getAmount()%1000;

        if(remainder == 0){
            return "Dispense " + (int) note.getAmount()/1000 + " LKR1000 notes!";
        }else{
            note.setAmount(remainder);
            return "Dispense " + (int) remainderFromPreviousDivision/1000 + " LKR1000 notes!\n" + successor.dispense(note);
        }
    }
}
