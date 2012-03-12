package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

import org.pgscala.test.TestDb

class BooleanTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("About to test a boolean converter"){
    info("I want to test if PGNullableBooleanConverter works correctly, both in 2 way conversion")

    scenario("boolean to string Nr. 1"){
      given ("a starting boolean value of true")
      val t = true
      when ("that value is converted to String")
      val res = PGNullableBooleanConverter booleanToString t
      then ("""It should return a String value "t"""")
      res should equal("t")
    }

    scenario("boolean to string Nr. 2"){
      given("a starting boolean value of false")
      val f = false
      when ("that value is converted to String")
      val res = PGNullableBooleanConverter booleanToString f
      then ("""It should return a String value "f"""")
      res should equal("f")
    }

    scenario("string to boolean Nr. 1"){
      given ("""a starting String value of "t"""")
      val t = "t"
      when ("that value is converted to String")
      val res = PGNullableBooleanConverter stringToBoolean t
      then ("""It should return a boolean value true""")
      res should equal(true)
    }

    scenario("string to boolean Nr. 2"){
      given ("""a starting String value of "f"""")
      val f = "f"
      when ("that value is converted to String")
      val res = PGNullableBooleanConverter stringToBoolean f
      then ("""It should return a boolean value false""")
      res should equal(false)
    }

    scenario("POSTGRES: Boolean to String Nr. 1"){
      TestDb.using{ db =>
        val t = db.row("""SELECT true::boolean;"""){rS => rS.get[Boolean](1)}.get
        given ("a starting boolean value of true")
        when ("that value is converted to String")
        val res = t.toString
        then ("""It should return a String value "%s"""" format res)
        res should equal ("true")
      }
    }

    scenario("POSTGRES: Boolean to String Nr. 2"){
      TestDb.using{ db =>
        val t = db.row("""SELECT false::boolean;"""){rS => rS.get[Boolean](1)}.get
        given ("a starting boolean value of false")
        when ("that value is converted to String")
        val res = t.toString
        then ("""It should return a String value "%s"""" format res)
        res should equal ("false")
      }
    }

    scenario("POSTGRES: String to Boolean Nr. 1"){
      TestDb.using{ db =>
        val t = db.row("""SELECT true::boolean;"""){rS => rS.get[String](1)}.get
        given ("a starting String value of true")
        when ("that value is converted to Boolean")
        val res = t.toString()
        then ("""It should return a String value "%s"""" format t)
        t should equal ("t")
      }
    }

    scenario("POSTGRES: String to Boolean Nr. 2"){
      TestDb.using{ db =>
        val t = db.row("""SELECT false::boolean;"""){rS => rS.get[String](1)}.get
        given ("a starting String value of true")
        when ("that value is converted to Boolean")
        val res = t.toString()
        then ("""It should return a String value "%s"""" format t)
        t should equal ("f")
      }
    }

  }
}
