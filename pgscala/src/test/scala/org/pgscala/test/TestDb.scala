package org.pgscala
package test

object TestDbCreds extends PGCredentials(
    host = "localhost"
  , port = 5432
  , dbName = "pgscala_test"
  , user = "pgscala"
  , pass = "changeit"
  )

object TestDb extends PGSimple(TestDbCreds)