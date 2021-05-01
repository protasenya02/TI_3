package com.bsuir.cryptohash;

public class HashCode {
  private long hashCode;
  private long startValue;

  public HashCode() {
    calculateStartValue();
  }

  public long getHashCode() {
    return hashCode;
  }

  public void calculateHashCode(String message, long r) {

    long hashCode = 0;
    long currentH = startValue;

    for (int i = 0; i < message.length(); i++) {
      hashCode = ((currentH + message.charAt(i))*(currentH + message.charAt(i))) % r;
      currentH = hashCode;
    }

    System.out.println("Hash code: " + hashCode );

    this.hashCode = hashCode;
  }

  private void calculateStartValue() {
    startValue = generateStartValue(2,10000);    // H0
  }

  public long generateStartValue(long leftLimit, long  rightLimit) {
    long generatedValue = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

    return generatedValue;
  }


}
