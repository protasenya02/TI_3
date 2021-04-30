package com.bsuir;

import java.util.Scanner;

public class DigitalSignature {
  private long p;           // prime number
  private long q;           // prime number
  private long r;
  private long f;           // Euler function
  private long e;           // public exponent
  private long d;           // private exponent
  private long S;           // digital signature
  private String Message;   // entered message
  private long startH;

  public void setP(long p) {
    this.p = p;
  }

  public void setQ(long q) {
    this.q = q;
  }

  public void setMessage(String message) {
    Message = message;
  }

  public String getMessage() {
    return Message;
  }

  public void inputP() {

    System.out.println("Input simple number p:");
    Scanner input = new Scanner(System.in);
    setP(input.nextLong());
  }

  public void inputQ() {

    System.out.println("Input simple number q:");
    Scanner input = new Scanner(System.in);
    setQ(input.nextLong());
  }

  public void inputMessage() {

    System.out.println("Input message:");
    Scanner input = new Scanner(System.in);
    setMessage(input.nextLine());
  }

  private void calculateR() {
    r = p*q;
  }

  private void calculateF() {
    f = (p-1)*(q-1);
  }

  private void calculateE() {

    boolean wasFound = false;

    while (!wasFound) {

      long randomNumber = generateRandomNumber(2,f);

      if ( isPrime(randomNumber) && (isCoprime(randomNumber, f))) {

        wasFound = true;
        e = randomNumber;

      }
    }
  }

  private void calculateD() {

    Triple temp = extendedEuclid(f, e);
    d = temp.getY();

    if (d < 0) {
      d += f;
    }
  }

  private void calculateStartH() {
    startH = generateRandomNumber(2,10000);    // H0
  }

  public long generateRandomNumber(long leftLimit, long  rightLimit) {

    long generatedNumber = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

    return generatedNumber;
  }

  // check number is prime
  private boolean isPrime(long numb) {

    for (long i=2; i <= Math.sqrt(numb); i++) {

      if (numb % i == 0) {
        return false;
      }

    }

    return true;
  }

  // check is two numbers are mutually prime numbers
  private boolean isCoprime(long n1, long n2) {
    return (gcdByEuclid(n1, n2) == 1);
  }

  // search greatest common divisor
  private long gcdByEuclid(long n1, long n2) {

    if (n2 == 0) {
      return n1;
    }

    return gcdByEuclid(n2, n1 % n2);
  }

  private Triple extendedEuclid(long a, long b) {

    if (b == 0) {

      return new Triple(a,1,0);

    } else {

      Triple temp = extendedEuclid(b, a % b);

      long d = temp.getD();
      long x = temp.getY();
      long y = temp.getX() - temp.getY() * (a / b);

      return new Triple(d,x,y);
    }
  }

  public void calculateKeys() {

    calculateR();
    calculateF();
    calculateE();
    calculateD();

  }


  public void outputOpenKey() {
    System.out.println("Open key: {"+e+","+r+"}" );
  }

  public void outputPrivateKey() {
    System.out.println("Private key: {"+d+","+r+"}" );
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

  private long calculateHashCode(String message) {

    long hashCode = 0;
    long currentH = startH;

    for (int i = 0; i < message.length(); i++) {
      hashCode = ((currentH + message.charAt(i))*(currentH + message.charAt(i))) % r;
      currentH = hashCode;
    }

    System.out.println("Hash code: " + hashCode );

    return hashCode;
  }


  public void signMessage() {

    calculateStartH();
    long hashCode = calculateHashCode(getMessage());
    S = modExp(hashCode,d,r);
    System.out.println("Signed message: {"+getMessage()+","+ S +"}");

  }

  public void checkMessageSignature() {

    long m = modExp(S,e,r);
    long hashCode = calculateHashCode(getMessage());

    if (m == hashCode)
      System.out.println("Message is original.");
    else
      System.out.println("Message is fake.");

  }




}


