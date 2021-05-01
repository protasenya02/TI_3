package com.bsuir.digitalSignature.rsa;

public class RSAPrivateKey {
  private long d;           // private exponent
  private long r;           // p*q

  public long getD() {
    return d;
  }

  public long getR() {
    return r;
  }

  public RSAPrivateKey(long d, long r) {
    this.d = d;
    this.r = r;
  }

  public void output() {
    System.out.println("Private key: {"+d+","+r+"}" );
  }

}
