package hr.element.pgscala.converters

object PGNullableDoubleConverter extends PGTypeConverter[Option[Double]] {
  def toString(value: Option[Double]): String =
    value match {
      case Some(d) =>
        PGDoubleConverter.toString(d)
      case _ =>
        null
    }

  def fromString(value: String): Option[Double] =
    if(value != null && value.nonEmpty) {
      Some(PGDoubleConverter.fromString(value))
    }
    else {
      None
    }
}