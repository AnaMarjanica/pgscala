package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

import org.pgscala._
import test.TestDb

class BigDecimalTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {

  feature("about to test a BigDecimal converter"){
    info("I want to test if PGNullableBigDecimalConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the BigDecimal special cases")

//    scenario("BigDecimal to String Nr. 1"){
//      //select 3.1415926530119026040722614947737296840070086399613::numeric;
//      val bd = BigDecimal(103993)/BigDecimal.valueOf(33102)
//      given ("a starting BigDecimal value of irrational number %s" format bd)
//      when ("that value is converted to String")
//      val res = PGNullableBigDecimalConverter bigDecimalToString bd
//      then ("""It should return a String value "%s"""" format res)
//      res should equal (bd.toString)
//    }
//
//    scenario("BigDecimal to String Nr. 2"){
//      val bd = BigDecimal(0)
//      given ("a starting BigDecimal value of %s" format bd)
//      when ("that value is converted to String")
//      val res = PGNullableBigDecimalConverter bigDecimalToString bd
//      then ("""It should return a String value "%s"""" format res)
//      res should equal (bd.toString)
//    }
//
//    scenario("BigDecimal to String Nr. 3"){
//      val bd = BigDecimal(1)
//      given ("a starting BigDecimal value of %s" format bd)
//      when ("that value is converted to String")
//      val res = PGNullableBigDecimalConverter bigDecimalToString bd
//      then ("""It should return a String value "%s"""" format res)
//      res should equal (bd.toString)
//    }
//
//    scenario("BigDecimal to String Nr. 4"){
//      //select 0.33333333333333333333333333333333333333333333333333::decimal;
//      val bd = BigDecimal(1)/BigDecimal(3)
//      given ("a starting BigDecimal value for rational number %s" format bd)
//      when ("that value is converted to String")
//      val res = PGNullableBigDecimalConverter bigDecimalToString bd
//      then ("""It should return a String value "%s"""" format res)
//      res should equal (bd.toString)
//    }

//    scenario("String to BigDecimal Nr. 1"){
//      val s = "0"
//      given("""a starting String value of "%s""" format s)
//      when("that value is converted to BigDecimal")
//      val res = PGNullableBigDecimalConverter stringToBigDecimal s
//      then("it should return a BigDecimal value %s" format res)
//      res should equal (BigDecimal(0))
//    }
//
//    scenario("String to BigDecimal Nr. 2"){
//      val s = "1"
//      given("""a starting String value of "%s""" format s)
//      when("that value is converted to BigDecimal")
//      val res = PGNullableBigDecimalConverter stringToBigDecimal s
//      then("it should return a BigDecimal value %s" format res)
//      res should equal (BigDecimal(1))
//    }
//
//    scenario("String to BigDecimal Nr. 3"){
//      val s = "0.33333333333333333333333333333333333333333333333333"
//      val bd = BigDecimal(1)/BigDecimal(3)
//      given("""a starting String value of "%s""" format s)
//      when("that value is converted to BigDecimal")
//      val res = PGNullableBigDecimalConverter stringToBigDecimal s
//      then("it should return a BigDecimal value %s" format res)
//      res should equal (bd)
//    }
//
//    scenario("String to BigDecimal Nr. 4"){
//      val s = "3.1415926530119026040722614947737296840070086399613"
//      val bd =  BigDecimal(103993)/BigDecimal(33102)
//      given("""a starting String value of "%s""" format s)
//      when("that value is converted to BigDecimal")
//      val res = PGNullableBigDecimalConverter stringToBigDecimal s
//      then("it should return a BigDecimal value %s" format res)
//      res should equal (bd)
//    }

    scenario("POSTGRES: BigDecimal to String Nr. 1"){
      TestDb.using{ db =>
        val bd =
          db.row("""
            SELECT 0.3333333333333333333333333::numeric;
          """){rS => rS.get[BigDecimal](1)
          }.get
        given ("a starting BigDecimal value of irrational number %s" format bd)
        when ("that value is converted to String")
        val res = bd.toString
        then ("""It should return a String value "%s"""" format res)
        res should equal ("0.3333333333333333333333333")
      }
    }


    scenario("POSTGRES: BigDecimal to String Nr. 2"){
      TestDb.using{ db =>
        val bd =
          db.row("""
            SELECT 0;
          """){rS => rS.get[BigDecimal](1)
          }.get
        given ("a starting BigDecimal value of %s" format bd)
        when ("that value is converted to String")
        val res = bd.toString
        then ("""It should return a String value "%s"""" format res)
        res should equal ("0")
      }
    }


    scenario("POSTGRES: BigDecimal to String Nr. 3"){
      TestDb.using{ db =>
        val bd =
          db.row("""
            SELECT 1;
          """){rS => rS.get[BigDecimal](1)
          }.get

        given ("a starting BigDecimal value of %s" format bd)
        when ("that value is converted to String")
        val res = bd.toString
        then ("""It should return a String value "%s"""" format res)
        res should equal ("1")
      }
    }

    scenario("POSTGRES: BigDecimal to String Nr. 4"){
      TestDb.using{ db =>
        val bd =
          db.row("""
            SELECT 3.145926535897931;
          """){rS => rS.get[BigDecimal](1)
          }.get
        given ("a starting BigDecimal value for irrational number %s" format bd)
        when ("that value is converted to String")
        val res = bd.toString
        then ("""It should return a String value "%s"""" format res)
        res should equal ("3.145926535897931")
      }
    }

    scenario("POSTGRES: String to BigDecimal Nr. 1"){
      TestDb.using{ db =>
        val s = db.row("""SELECT 3.145926535897931;"""){rS => rS.get[String](1)}.get
        given ("a starting String value for irrational number %s" format s)
        when ("that value is converted to BigDecimal")
        val res = BigDecimal(s)
        then ("""It should return a BigDecimal value "%s"""" format res)
        res should equal (BigDecimal("3.145926535897931"))
      }
    }

    scenario("POSTGRES: String to BigDecimal Nr. 2"){
      TestDb.using{ db =>
        val s = db.row("""SELECT 0.3333333333333333;"""){rS => rS.get[String](1)}.get
        given ("a starting String value for rational number %s" format s)
        when ("that value is converted to BigDecimal")
        val res = BigDecimal(s)
        then ("""It should return a BigDecimal value "%s"""" format res)
        res should equal (BigDecimal("0.3333333333333333"))
      }
    }

    scenario("POSTGRES: String to BigDecimal Nr. 3"){
      TestDb.using{ db =>
        val s = db.row("""SELECT 0;"""){rS => rS.get[String](1)}.get
        given ("a starting String value for %s" format s)
        when ("that value is converted to BigDecimal")
        val res = BigDecimal(s)
        then ("""It should return a BigDecimal value "%s"""" format res)
        res should equal (BigDecimal("0"))
      }
    }

    scenario("POSTGRES: String to BigDecimal Nr. 4"){
      TestDb.using{ db =>
        val s = db.row("""SELECT 1;"""){rS => rS.get[String](1)}.get
        given ("a starting String value for %s" format s)
        when ("that value is converted to BigDecimal")
        val res = BigDecimal(s)
        then ("""It should return a BigDecimal value "%s"""" format res)
        res should equal (BigDecimal("1"))
      }
    }


  }
}
