package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import scala.util.Random
import org.pgscala.test.TestDb

class LongTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("About to test an Long converter"){
    info("I want to test if PGNullableLongConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Long boundary cases")

    scenario("Long to String Nr. 1"){
      given ("a starting Long value of Long.MaxValue")
      val n = Long.MaxValue
      when ("that value is converted to String")
      val res = PGNullableLongConverter longToString n
      then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
     }
    scenario("Long to String Nr. 2"){
      given ("a starting Long value of Long.MinValue")
      val n = Long.MinValue
      when ("that value is converted to String")
      val res = PGNullableLongConverter longToString n
      then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }
    scenario("Long to String Nr. 3"){
      given ("a starting Long value of 0")
      val n = 0L
      when ("that value is converted to String")
      val res = PGNullableLongConverter longToString n
      then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }
    scenario("Long to String Nr. 4"){
      val r = Random
      val n = r.nextLong
      given ("a starting random Long value of %d" format n)
      when ("that value is converted to String")
      val res = PGNullableLongConverter longToString n
      then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }

    scenario("String to Long Nr. 1"){
      info("test for select 9223372036854775807::bigint;")
      given ("a starting String value of Long.MaxValue")
      val n = "9223372036854775807"
      when ("that value is converted to Long")
      val res = PGNullableLongConverter stringToLong n
      then ("It should return an Long value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Long Nr. 2"){
      info("test for select  -9223372036854775808;")
      given ("a starting String value of Long.MinValue")
      val n = "-9223372036854775808"
      when ("that value is converted to Long")
      val res = PGNullableLongConverter stringToLong n
      then ("It should return an Long value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Long Nr. 3"){
      info("test for select 0::bigint;")
      given ("""a starting String value of "0"""")
      val n = "0"
      when ("that value is converted to Long")
      val res = PGNullableLongConverter stringToLong n
      then ("It should return an Long value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Long Nr. 4"){
      val r = Random
      val n = r.nextLong.toString
      given ("""a starting String value of random Long number "%s"""" format n)
      when ("that value is converted to Long")
      val res = PGNullableLongConverter stringToLong n
      then ("It should return an Long value %s" format n)
      res.toString should equal(n)
    }


    /*
     * POSTGRES
     */
    scenario("POSTGRESQL: Long to String Nr. 1"){
      TestDb.using{ db =>
        val n = db.row("select 9223372036854775807::bigint;"){rS => rS.get[Long](1)}.get
        given(" a starting Long value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("9223372036854775807")
      }
    }

    scenario("POSTGRESQL: Long to String Nr. 2"){
      TestDb.using{ db =>
        val n = db.row("select -9223372036854775807::bigint;"){rS => rS.get[Long](1)}.get
        given(" a starting Long value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("-9223372036854775807")
      }
    }

    scenario("POSTGRESQL: Long to String Nr. 3"){
      TestDb.using{ db =>
        val n = db.row("select 0::bigint;"){rS => rS.get[Long](1)}.get
        given(" a starting Long value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("0")
      }
    }

    scenario("POSTGRESQL: String to Long Nr. 1"){
      TestDb.using{ db =>
        val n = db.row("select 9223372036854775807::bigint;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Long")
        val res = n.toLong
        then ("It should return a Long value %s" format res)
        res should equal(9223372036854775807L)
      }
    }

    scenario("POSTGRESQL: String to Long Nr. 2"){
      TestDb.using{ db =>
        val n = db.row("select -9223372036854775807::bigint;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Long")
        val res = n.toLong
        then ("It should return a Long value %s" format res)
        res should equal(-9223372036854775807L)
      }
    }

    scenario("POSTGRESQL: String to Long Nr. 3"){
      TestDb.using{ db =>
        val n = db.row("select 0::bigint;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Long")
        val res = n.toLong
        then ("It should return a Long value %s" format res)
        res should equal(0L)
      }
    }

  }
}
