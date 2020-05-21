package com.eranda;

public class Notepad {

    String copiedText;

    public void setLastState(String copiedText){
        this.copiedText = copiedText;
    }

    public String getCopiedText() {
        return copiedText;
    }

    public Memento save(){
        return new Memento(getCopiedText());
    }

    public void revert(Memento memento){
        copiedText = memento.getCopiedText();
    }

    @Override
    public String toString() {
        return "Notepad{" +
                "copiedText='" + copiedText + '\'' +
                '}';
    }
}
