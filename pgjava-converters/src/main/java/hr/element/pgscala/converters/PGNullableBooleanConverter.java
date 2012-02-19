package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.lang.Boolean;

public enum PGNullableBooleanConverter implements StringConverter<Boolean> {
  INSTANCE;

  public static final String pgType = "boolean";

  @ToString
  public static String booleanToString(final Boolean b) {
    return null == b ? null : b ? "t" : "f";
  }

  @FromString
  public static Boolean stringToBoolean(final String b) {
    return null == b ? null : b.equals("t") ? Boolean.TRUE : Boolean.FALSE;
  }

// ----------------------------------------------------------------------------

  public String convertToString(final Boolean b) {
    return booleanToString(b);
  }

  public Boolean convertFromString(final Class<? extends Boolean> clazz, final String b) {
    return stringToBoolean(b);
  }
}