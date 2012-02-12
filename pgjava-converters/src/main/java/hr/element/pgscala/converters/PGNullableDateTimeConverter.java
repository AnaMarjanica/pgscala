package hr.element.pgscala.converters;

import org.joda.convert.*;

import org.joda.time.*;
import org.joda.time.format.*;

public class PGNullableDateTimeConverter {
  public static final String pgType = "timestamptz";

  private static final DateTimeFormatter dateTimeFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSZZ");

  @ToString
  public static String toPGString(final DateTime dT) {
    return null == dT ? null : dateTimeFormat.print(dT);
  }

  @FromString
  public static DateTime fromPGString(final String dT) {
    return null == dT ? null : dateTimeFormat.parseDateTime(dT);
  }
}
