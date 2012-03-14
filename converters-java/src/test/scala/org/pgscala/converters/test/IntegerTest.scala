package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.pgscala.test.TestDb

class IntegerTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("About to test an Integer converter"){
    info("I want to test if PGNullableIntegerConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Integer boundary cases")

    scenario("Integer to String Nr. 1"){
      given ("a starting Integer value of Int.MaxValue")
      val n = Int.MaxValue
      when ("that value is converted to String")
      val res = PGNullableIntegerConverter integerToString n
      then ("""It should return a String value "%d"""" format Int.MaxValue)
      res should equal(Int.MaxValue.toString)
    }

    scenario("Integer to String Nr. 2"){
      given ("a starting Integer value of Int.MinValue")
      val n = Int.MinValue
      when ("that value is converted to String")
      val res = PGNullableIntegerConverter integerToString n
      then ("""It should return a String value "%d"""" format Int.MinValue)
      res should equal(Int.MinValue.toString)
    }

    scenario("Integer to String Nr. 3"){
      given ("a starting Integer value of 0")
      val n = 0
      when ("that value is converted to String")
      val res = PGNullableIntegerConverter integerToString n
      then ("""It should return a String value "0"""")
      res should equal(n.toString)
    }
    scenario("String to Integer Nr. 1"){
      given ("a starting String value of Int.MaxValue")
      val n = Int.MaxValue.toString
      when ("that value is converted to Integer")
      val res = PGNullableIntegerConverter stringToInteger n
      then ("""It should return an Integer value %d""" format Int.MaxValue)
      res.toString should equal(n)
    }

    scenario("String to Integer Nr. 2"){
      given ("a starting String value of Int.MinValue")
      val n = Int.MinValue.toString
      when ("that value is converted to Integer")
      val res = PGNullableIntegerConverter stringToInteger n
      then ("""It should return an Integer value %d""" format Int.MinValue)
      res.toString should equal(n)
    }

    scenario("String to Integer Nr. 3"){
      given ("""a starting String value "0""")
      val n = "0"
      when ("that value is converted to Integer")
      val res = PGNullableIntegerConverter stringToInteger n
      then ("It should return an Integer value 0")
      res.toString should equal(n)
    }

    /*
     * POSTGRES
     */

    scenario("POSTGRESQL: Integer to String Nr. 1"){
      TestDb.using{ db =>
        val n = db.row("select 0::integer;"){rS => rS.get[Int](1)}.get
        given(" a starting Integer value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("0")
      }
    }

    scenario("POSTGRESQL: Integer to String Nr. 2"){
      TestDb.using{ db =>
        val n = db.row("select -2147483647::integer;"){rS => rS.get[Int](1)}.get
        given(" a starting Integer value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("-2147483647")
      }
    }

    scenario("POSTGRESQL: Integer to String Nr. 3"){
      TestDb.using{ db =>
        val n = db.row("select 2147483647::integer;"){rS => rS.get[Int](1)}.get
        given(" a starting Integer value for %s" format n)
        when("that value is converted to String")
        val res = n.toString
        then ("It should return a String value %s" format res)
        res should equal("2147483647")
      }
    }

    scenario("POSTGRESQL: String to Integer Nr. 1"){
      TestDb.using{ db =>
        val n = db.row("select 0::integer;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Integer")
        val res = n.toInt
        then ("It should return an Integer value %s" format res)
        res should equal(0)
      }
    }

    scenario("POSTGRESQL: String to Integer Nr. 2"){
      TestDb.using{ db =>
        val n = db.row("select -2147483647::integer;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Integer")
        val res = n.toInt
        then ("It should return an Integer value %s" format res)
        res should equal(-2147483647)
      }
    }

    scenario("POSTGRESQL: String to Integer Nr. 3"){
      TestDb.using{ db =>
        val n = db.row("select 2147483647::integer;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format n)
        when("that value is converted to Integer")
        val res = n.toInt
        then ("It should return an Integer value %s" format res)
        res should equal(2147483647)
      }
    }


  }
}
