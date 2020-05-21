package com.eranda;

public class Memento {

    String copiedText;

    public Memento(String copiedText) {
        this.copiedText = copiedText;
    }

    public String getCopiedText() {
        return copiedText;
    }
}
