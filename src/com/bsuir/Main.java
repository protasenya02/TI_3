package com.bsuir;

import com.bsuir.digitalSignature.DigitalSignature;

public class Main {

    public static void main(String[] args) {

        DigitalSignature signature = new DigitalSignature();
        signature.signMessage();
        signature.checkMessageSignature();
    }
}
