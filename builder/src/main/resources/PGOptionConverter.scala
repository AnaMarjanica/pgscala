package org.pgscala.converters

{ imports }

/** Do not edit - generated in Builder / { builder } */

object PGOption{ scalaUpperType }Converter extends PGConverter[Option[{ scalaType }]] {
  val PGType = PG{ scalaUpperType }Converter.PGType

  def toPGString({ optionScalaVar }: Option[{ scalaType }]): String =
    { optionScalaVar } match {
      case None =>
        null
      case Some({ scalaVar }) =>
        PG{ scalaUpperType }Converter.toPGString({ scalaVar })
    }

  def fromPGString({ scalaVar }: String): Option[{ scalaType }] =
    { scalaVar } match {
      case null | "" =>
        None
      case { optionScalaVar } =>
        Some(PG{ scalaUpperType }Converter.fromPGString({ optionScalaVar }))
    }
}
