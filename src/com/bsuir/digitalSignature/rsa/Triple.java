package com.bsuir.digitalSignature.rsa;

public class Triple {

  private final long d;
  private final long x;
  private final long y;

  public Triple(long d, long x, long y) {

    this.d = d;
    this.x = x;
    this.y = y;

  }

  public long getD() {
    return d;
  }

  public long getX() {
    return x;
  }

  public long getY() {
    return y;
  }
}
