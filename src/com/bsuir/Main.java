package com.bsuir;

public class Main {

    public static void main(String[] args) {

        DigitalSignature signature = new DigitalSignature();

        signature.inputP();
        signature.inputQ();

        signature.calculateKeys();

        signature.outputOpenKey();
        signature.outputPrivateKey();

        signature.inputMessage();
        signature.signMessage();

        signature.checkMessageSignature();





    }
}
