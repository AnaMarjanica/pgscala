.class public hr.element.pgscala.converters.PGNullableConverter
.super java/lang/Object

; Do not edit - generated in PGBuilder / JConverterBuilder.scala

.method public static fromPGString(Ljava/lang/String;){ jasminType }
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic hr/element/pgscala/converters/PGNullable{ upperType }Converter.stringTo{ upperType }(Ljava/lang/String;){ jasminType }
  areturn
.end method

.method public static toPGString({ jasminType })Ljava/lang/String;
  .limit locals 1
  .limit stack 2
  aload_0
  invokestatic hr/element/pgscala/converters/PGNullable{ upperType }Converter.{ lowerType }ToString({ jasminType })Ljava/lang/String;
  areturn
.end method
