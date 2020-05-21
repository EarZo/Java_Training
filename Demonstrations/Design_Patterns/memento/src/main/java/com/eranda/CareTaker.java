package com.eranda;

import java.util.Stack;

public class CareTaker {

    Stack<Memento> history = new Stack<Memento>();

    public void save(Notepad notepad){
        history.push(notepad.save());
    }

    public void revert(Notepad notepad){
        if (history.isEmpty()){
            System.out.println("Cannot undo!");
        }else{
            notepad.revert(history.pop());
        }
    }

}
