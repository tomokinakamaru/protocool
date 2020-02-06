package com.github.tomokinakamaru.protocool.error;

public class SignatureConflict extends RuntimeException {

  public SignatureConflict(String s) {
    super("Signature conflict: " + s);
  }
}
