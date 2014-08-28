package org.pgscala
package builder
package converters

object PGFloatConverterBuilder
    extends PGPredefConverterBuilder {

  val scalaClazz = "scala.Float"

  override val javaClazz = "java.lang.Float"

  override val defaultValue = """0.0f"""
}
