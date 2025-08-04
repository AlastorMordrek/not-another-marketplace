package com.silas_mordrek.not_another_marketplace.base_model;

import        org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmailTest {

  @Test
  void valid_p_returnsTrueForValid () {
    assertTrue(Email.valid_p("pepe@gmail.com"));
  }

  @Test
  void valid_p_returnsFalseForEmpty () {
    assertFalse(Email.valid_p(""));
  }

  @Test
  void valid_p_returnsFalseForLong () {
    assertFalse(Email.valid_p(
      "a1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890@gmail.com"
    ));
  }

  @Test
  void valid_p_returnsFalseForNull () {
    assertFalse(Email.valid_p(null));
  }
}
