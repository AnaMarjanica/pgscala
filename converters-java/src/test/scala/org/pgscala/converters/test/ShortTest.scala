package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import scala.util.Random
import org.pgscala.test.TestDb

class ShortTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("About to test an Short converter"){
    info("I want to test if PGNullableShortConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Short boundary cases")

    scenario("Short to String Nr. 1"){
      given ("a starting Short value of Short.MaxValue")
      val n = Short.MaxValue
      when ("that value is converted to String")
      val res = PGNullableShortConverter shortToString n
      then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
     }
    scenario("Short to String Nr. 2"){
      given ("a starting Short value of Short.MinValue")
      val n = Short.MinValue
      when ("that value is converted to String")
      val res = PGNullableShortConverter shortToString n
      then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }
    scenario("Short to String Nr. 3"){
      given ("a starting Short value of 0")
      val n: Short = 0
      when ("that value is converted to String")
      val res = PGNullableShortConverter shortToString n
      then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }

    scenario("String to Short Nr. 1"){
      info("test for select 32767::smallint;")
      given ("a starting String value of Short.MaxValue")
      val n = "32767"
      when ("that value is converted to Short")
      val res = PGNullableShortConverter stringToShort n
      then ("It should return an Short value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Short Nr. 2"){
      info("test for select -32767::smallint;")
      given ("a starting String value of Short.MinValue")
      val n = "-32767"
      when ("that value is converted to Short")
      val res = PGNullableShortConverter stringToShort n
      then ("It should return an Short value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Short Nr. 3"){
      info("test for select 0::smallint;")
      given ("""a starting String value of "0"""")
      val n = "0"
      when ("that value is converted to Short")
      val res = PGNullableShortConverter stringToShort n
      then ("It should return an Short value %s" format n)
      res.toString should equal(n)
    }

    /*
     * POSTGRES
     */

    scenario("POSTGRESQL: Short to String Nr. 1"){
      TestDb.using{ db =>
        val n = db.row("select 32767::smallint;"){rS => rS.get[Short](1)}.get
        given(" a starting Short value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("32767")
      }
    }

    scenario("POSTGRESQL: Short to String Nr. 2"){
      TestDb.using{ db =>
        val n = db.row("select -32767::smallint;"){rS => rS.get[Short](1)}.get
        given(" a starting Short value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("-32767")
      }
    }

    scenario("POSTGRESQL: Short to String Nr. 3"){
      TestDb.using{ db =>
        val n = db.row("select 0::smallint;"){rS => rS.get[Short](1)}.get
        given(" a starting Short value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("0")
      }
    }

    scenario("POSTGRESQL: String to Short Nr. 1"){
      TestDb.using{ db =>
        val n = db.row("select 32767::smallint;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Short")
        val res = n.toShort
        then ("It should return a Short value %s" format res)
        res should equal(32767)
      }
    }

    scenario("POSTGRESQL: String to Short Nr. 2"){
      TestDb.using{ db =>
        val n = db.row("select -32767::smallint;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Short")
        val res = n.toShort
        then ("It should return a Short value %s" format res)
        res should equal(-32767)
      }
    }

    scenario("POSTGRESQL: String to Short Nr. 3"){
      TestDb.using{ db =>
        val n = db.row("select 0::smallint;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Short")
        val res = n.toShort
        then ("It should return a Short value %s" format res)
        res should equal(0)
      }
    }

  }
}
