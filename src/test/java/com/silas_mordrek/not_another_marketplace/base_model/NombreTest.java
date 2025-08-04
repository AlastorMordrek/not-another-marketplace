package com.silas_mordrek.not_another_marketplace.base_model;

import        org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NombreTest {

  @Test
  void valid_p_returnsTrueForValid () {
    assertTrue(Nombre.valid_p("Silas Mordrek"));
  }

  @Test
  void valid_p_returnsFalseForEmpty () {
    assertFalse(Nombre.valid_p(""));
  }

  @Test
  void valid_p_returnsFalseForLong () {
    assertFalse(Nombre.valid_p(
      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    ));
  }

  @Test
  void valid_p_returnsFalseForNull () {
    assertFalse(Nombre.valid_p(null));
  }
}
