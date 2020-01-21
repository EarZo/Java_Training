/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EncapsulationDemo;

/**
 *
 * @author Eranda
 */
public class AnonymousInnerClassEncapsulation {

    public String AnonymousInnerClassMethod() {
        
        return new Object(){
            
            String anonymousInnerClassMessage = "This is the anonymous inner class!";

            @Override
            public String toString() {
                return this.anonymousInnerClassMessage;
            }
        }.toString();
    }
}
