package com.silas_mordrek.not_another_marketplace.base_model;

public class Nombre {
  public static boolean valid_p (String v) {
    return (v != null && !v.isEmpty() && v.length() <= 100
        && !v.trim().isEmpty());
  }
}
