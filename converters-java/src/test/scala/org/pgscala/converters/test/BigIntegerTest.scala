package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

import org.pgscala._
import test.TestDb


class BigIntegerTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("About to test a BigInteger converter"){
    info("I want to test if PGNullableBigIntegerConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the BigInteger boundary cases")

//    scenario("BigInteger to String Nr. 1"){
//      given ("a starting BigInteger value of 0")
//      val n = BigInteger.ZERO
//      when ("that value is converted to String")
//      val res = PGNullableBigIntegerConverter bigIntegerToString n
//      then ("""It should return a String value "0"""")
//      res should equal(BigInteger.ZERO.toString)
//    }
//
//    scenario("BigInteger to String Nr. 2"){
//      val n = BigInteger.valueOf(Long.MaxValue + 1)
//      given ("a starting BigInteger value of %d" format n)
//      when ("that value is converted to String")
//      val res = PGNullableBigIntegerConverter bigIntegerToString n
//      then ("""It should return a String value "%d"""" format n)
//      res should equal(n.toString)
//    }
//
//    scenario("BigInteger to String Nr. 3"){
//      val n = BigInteger.valueOf(Long.MinValue - 1)
//      given ("a starting BigInteger value of %d" format n)
//      when ("that value is converted to String")
//      val res = PGNullableBigIntegerConverter bigIntegerToString n
//      then ("""It should return a String value "%d"""" format n)
//      res should equal(n.toString)
//    }
//
//    scenario("String to BigInteger Nr. 1"){
//      given ("""a starting String value of "0"""")
//      val n = BigInteger.ZERO.toString
//      when ("that value is converted to BigInteger")
//      val res = PGNullableBigIntegerConverter stringToBigInteger n
//      then ("It should return a BigInteger value 0")
//      res should equal(BigInteger.ZERO)
//    }
//
//    scenario("String to BigInteger Nr. 2"){
//      val n = BigInteger.valueOf(Long.MaxValue + 1).toString
//      given ("""a starting String value of "%s"""" format n)
//      when ("that value is converted to BigInteger")
//      val res = PGNullableBigIntegerConverter stringToBigInteger n
//      then ("It should return a BigInteger value %s" format n)
//      res should equal(BigInteger.valueOf(Long.MaxValue + 1))
//    }
//
//    scenario("String to BigInteger Nr. 3"){
//      val n = BigInteger.valueOf(Long.MinValue - 1).toString
//      given ("""a starting String value of "%s""" format n)
//      when ("that value is converted to BigInteger")
//      val res = PGNullableBigIntegerConverter stringToBigInteger n
//      then ("It should return a BigInteger value %s" format n)
//      res should equal(BigInteger.valueOf(Long.MinValue - 1))
//    }
//
//

//    scenario("POSTGRES: BigInteger to String Nr. 1"){
//      TestDb.using{ db =>
//        val bi =
//          db.row("""
//            SELECT 0::bigint;
//          """){rS => rS.get[String](1)
//          }.get
//        given ("a starting BigInteger value of %s" format bi)
//        when ("that value is converted to String")
//        val res = PGNullableBigIntegerConverter bigIntegerToString bi
//        then ("""It should return a String value "%s"""" format res)
//        res should equal (bi.toString)
//      }
//    }
//
//    scenario("POSTGRES: BigInteger to String Nr. 2"){
//      TestDb.using{ db =>
//        val bi =
//          db.row("""
//            SELECT 9223372036854775807::bigint;
//          """){rS => rS.get[String](1)
//          }.get
//        given ("a starting BigInteger value of %s" format bi)
//        when ("that value is converted to String")
//        val res = PGNullableBigIntegerConverter bigIntegerToString bi
//        then ("""It should return a String value "%s"""" format res)
//        res should equal (bi.toString)
//      }
//    }

    scenario("POSTGRES: BigInteger to String Nr. 3"){
      TestDb.using{ db =>
        val bi =
          db.row("""
            SELECT -9223372036854775807::bigint;
          """){rS => rS.get[BigInt](1)
          }.get
        given ("a starting BigInteger value of %s" format bi)
        when ("that value is converted to String")
        val res = bi.toString
        then ("""It should return a String value "%s"""" format res)
        res should equal ("-9223372036854775807")
      }
    }

//    scenario("POSTGRES: String to BigInteger Nr. 1"){
//      TestDb.using{ db =>
//        val s = db.row("""
//            SELECT 0::bigint;
//          """){rS => rS.get[String](1)
//          }.get
//        given ("a starting String value of %s" format s)
//        when ("that value is converted to BigInteger")
//        val res = PGNullableBigIntegerConverter stringToBigInteger s
//        then ("""It should return a BigInteger value "%s"""" format res)
//        res.toString should equal (s)
//      }
//    }
//
//    scenario("POSTGRES: String to BigInteger Nr. 2"){
//      TestDb.using{ db =>
//        val s = db.row("""
//             SELECT 9223372036854775807::bigint;
//          """){rS => rS.get[String](1)
//          }.get
//        given ("a starting String value of %s" format s)
//        when ("that value is converted to BigInteger")
//        val res = PGNullableBigIntegerConverter stringToBigInteger s
//        then ("""It should return a BigInteger value "%s"""" format res)
//        res.toString should equal (s)
//      }
//    }

    scenario("POSTGRES: String to BigInteger Nr. 3"){
      TestDb.using{ db =>
        val s = db.row("""
             SELECT -9223372036854775807::bigint;
          """){rS => rS.get[String](1)
          }.get
        given ("a starting String value of %s" format s)
        when ("that value is converted to BigInteger")
        val res = BigInt(s)
        then ("""It should return a BigInteger value "%s"""" format res)
        res should equal (BigInt("-9223372036854775807"))
      }
    }


  }
}
