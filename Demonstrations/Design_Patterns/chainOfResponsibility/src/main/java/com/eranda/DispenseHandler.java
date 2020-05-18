package com.eranda;

import com.eranda.vault.Note;

public abstract class DispenseHandler {

    protected DispenseHandler successor;

    public void setSuccessor(DispenseHandler successor) {
        this.successor = successor;
    }

    public abstract String dispense(Note note);
}
