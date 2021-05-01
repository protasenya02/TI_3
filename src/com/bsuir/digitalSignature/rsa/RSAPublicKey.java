package com.bsuir.digitalSignature.rsa;

public class RSAPublicKey {
    private long e;           // public exponent
    private long r;           // p*q

    public long getE() {
      return e;
    }

    public long getR() {
      return r;
    }

    public RSAPublicKey(long e, long r) {
      this.e = e;
      this.r = r;
    }

    public void output() {
      System.out.println("Public key: {"+e+","+r+"}" );
    }

}
