package com.eranda;

import com.eranda.paper.Type;

import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Tester {

    public static void main(String[] args) {

        CopierMemory copierMemory = new CopierMemory();
        Type typeCopy = null;

        try {
            typeCopy = copierMemory.getLastScan(PaperType.A3);
            System.out.println(typeCopy);
        }catch (CloneNotSupportedException e){
            Logger.getLogger("Copier_Logger").log(SEVERE, e.getMessage(), e);
            System.out.println("Sorry, there was no scan found in memory!");
        }
    }
}
