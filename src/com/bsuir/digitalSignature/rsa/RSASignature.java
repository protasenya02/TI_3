package com.bsuir.digitalSignature.rsa;

public class RSASignature {
  private final RSAPrivateKey privateKey;
  private final RSAPublicKey publicKey;

  public RSASignature(long p, long q) {

    if(!isPrime(p)) throw new ArithmeticException("P should be prime.");
    if(!isPrime(q)) throw new ArithmeticException("Q should be prime.");

    long r = p * q;
    long f = (p-1)*(q-1);
    long e = calculateE(f);
    long d = calculateD(e,f);

    publicKey = new RSAPublicKey(e, r);
    privateKey = new RSAPrivateKey(d, r);
  }

  public RSAPrivateKey getPrivateKey() {
    return privateKey;
  }

  public RSAPublicKey getPublicKey() {
    return publicKey;
  }

  private long calculateE(long f) {

    long e = 0;
    boolean wasFound = false;

    while (!wasFound) {

      long randomNumber = generateRandomNumber(2,f);

      if ( isPrime(randomNumber) && (isCoprime(randomNumber, f))) {

        wasFound = true;
        e = randomNumber;
      }
    }

    return e;
  }

  private long calculateD(long e, long f) {

    Triple temp = extendedEuclid(f, e);
    long d = temp.getY();

    if (d < 0) {
      d += f;
    }

    return d;
  }

  private long generateRandomNumber(long leftLimit, long  rightLimit) {

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

}
