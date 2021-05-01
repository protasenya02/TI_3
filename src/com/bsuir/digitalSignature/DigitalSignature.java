package com.bsuir.digitalSignature;

import java.util.Scanner;
import com.bsuir.digitalSignature.rsa.*;
import com.bsuir.cryptohash.HashCode;

public class DigitalSignature {
  private String message;              // entered message
  private HashCode hashCode;           // message hash code
  private RSASignature rsaSignature;
  private long S;                       // digital signature

  public DigitalSignature() {
    long p = inputP();
    long q = inputQ();
    message = inputMessage();

    rsaSignature = new RSASignature(p, q);
    hashCode = new HashCode();
  }

  private long inputP() {
    System.out.println("Input simple number p:");
    Scanner input = new Scanner(System.in);

    return input.nextLong();
  }

  private long inputQ() {
    System.out.println("Input simple number q:");
    Scanner input = new Scanner(System.in);

    return input.nextLong();
  }

  private String inputMessage() {
    System.out.println("Input message:");
    Scanner input = new Scanner(System.in);

    return  input.nextLine();
  }

  // calculate x^y mod n
  private long modExp(long x, long y, long n) {

    if (y == 0) return 1;

    long z = modExp(x, y / 2, n);

    if (y % 2 == 0)
      return (z * z) % n;
    else
      return (x * z * z) % n;
  }

  public void signMessage() {

    RSAPrivateKey privateKey = rsaSignature.getPrivateKey();

    long r  = privateKey.getR();
    long d = privateKey.getD();
    hashCode.calculateHashCode(message, r);
    S = modExp(hashCode.getHashCode(),d,r);

    System.out.println("Signed message: {"+message+","+ S +"}");
  }

  public void checkMessageSignature() {

    System.out.println("Input message to check:");
    Scanner input = new Scanner(System.in);
    String messageToCheck = input.nextLine();

    RSAPublicKey publicKey = rsaSignature.getPublicKey();
    long r = publicKey.getR();
    long e = publicKey.getE();

    long m = modExp(S,e,r);
    hashCode.calculateHashCode(messageToCheck, r);

    if (m == hashCode.getHashCode())
      System.out.println("Message is original.");
    else
      System.out.println("Message is fake.");

  }


}


