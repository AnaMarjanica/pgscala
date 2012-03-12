package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.pgscala.test.TestDb

class StringTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("About to test an String converter"){
    info("I want to test if PGNullableStringConverter works correctly")

    scenario("String to String Nr. 1."){
      val t = """Ovo je test za String"""
      given(""" a starting String value "%s"""" format t)
      when ("that value is converted to String")
      val res = PGNullableStringConverter stringToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t)
    }

    scenario("POSTGRES: String to String Nr. 1."){
      TestDb.using{ db =>
        val t = db.row("select 'this is a \ntest\n'::text;"){rS => rS.get[String](1)}.get
        given(" a starting String value for %s" format t)
        when("that value is converted to String")
        val res = t.toString
        then ("It should return a String value %s" format res)
        res should equal("this is a \ntest\n")
      }
    }
  }
}
