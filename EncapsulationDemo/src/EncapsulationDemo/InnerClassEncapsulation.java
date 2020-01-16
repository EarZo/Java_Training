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
public class InnerClassEncapsulation {

    public String innerClassMethod() {

        class innerClass {

            String innerClassMessage;

            public innerClass(String innerClassMessage) {
                this.innerClassMessage = innerClassMessage;
            }

            @Override
            public String toString() {
                return this.innerClassMessage;
            }
        }
        
        return new innerClass("This is the inner class!").toString();
    }
}
