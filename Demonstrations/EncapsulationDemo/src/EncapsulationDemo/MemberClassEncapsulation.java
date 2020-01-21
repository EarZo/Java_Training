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
public class MemberClassEncapsulation {

    public String memberClassEncapsulationMessage;

    public MemberClassEncapsulation(String memberClassEncapsulationMessage) {
        this.memberClassEncapsulationMessage = memberClassEncapsulationMessage;
    }

    @Override
    public String toString() {
        return this.memberClassEncapsulationMessage;
    }

    class MemberClass {

        public String memberClassMessage;

        public MemberClass(String memberClassMessage) {
            this.memberClassMessage = memberClassMessage;
        }

        @Override
        public String toString() {
            return this.memberClassMessage;
        }
    }
}
