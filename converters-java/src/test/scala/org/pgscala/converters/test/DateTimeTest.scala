package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.pgscala.test.TestDb
import org.joda.time.Period



class DateTimeTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{

  val dF1 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSSZZ")

  feature("About to test a DateTime converter"){
    info("I want to test if PGNullableDateTimeConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the DateTime special timestamp inputs")

    scenario("Datetime to String Nr. 1."){
      val t = DateTime.now()
      given(" a starting current DateTime value %s" format t)
      when ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString(dF1))
    }

    scenario("Datetime to String Nr. 2."){
      val t = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss").parseDateTime("1970-01-01 00:00:00")
      given(" a starting DateTime value of epoch %s" format t)
      when ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString(dF1))
    }

    scenario("Datetime to String Nr. 3."){
      val t = LocalDate.now().toDateTimeAtStartOfDay()
      given(" a starting current DateTime value at midnight %s" format t)
      when ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString(dF1))
    }

    scenario("Datetime to String Nr. 4."){
      val t = DateTime.now().plus(1)
      given(" a starting DateTime value for tomorrow %s" format t)
      when ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString(dF1))
    }

    scenario("Datetime to String Nr. 5."){
      val t = DateTime.now().minus(1)
      given(" a starting DateTime value for yesterday %s" format t)
      when ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString(dF1))
    }

    scenario("String to DateTime Nr. 1"){
      val t = DateTime.now()
      given(" a starting String value of current DateTime %s" format t)
      when ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dF1.print(t)
      then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    scenario("String to DateTime Nr. 2"){
      val t = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss").parseDateTime("1970-01-01 00:00:00")
      given(""" a starting String value of epoch "%s"""" format t)
      when ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dF1.print(t)
      then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    scenario("String to DateTime Nr. 3"){
      val t = LocalDate.now().toDateTimeAtStartOfDay
      given(""" a starting String value of current date at midnight "%s""" format t)
      when ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dF1.print(t)
      then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    scenario("String to DateTime Nr. 4"){
      val t = DateTime.now().plus(1)
      given(""" a starting String value of tomorrow's date and time "%s""" format t)
      when ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dF1.print(t)
      then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    scenario("String to DateTime Nr. 5"){
      val t = DateTime.now().minus(1)
      given(""" a starting String value of yesterday's date and time "%s""" format t)
      when ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dF1.print(t)
      then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

//    /*
//     * POSTGRESQL
//     */
//    scenario("POSTGRESQL: String to DateTime Nr. 1"){
//      info("test for 'now'::timestamp")
//      val SQLtimestamp = "2012-02-23 14:27:01.994854"
//      given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
//      when("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
//      then ("It should return a DateTime value %s" format res.toString())
//      res.toString() should equal(SQLtimestamp)
//    }
//
//    scenario("POSTGRESQL: String to DateTime Nr. 2"){
//      info("test for 'epoch'::timestamp")
//      val SQLtimestamp = "1970-01-01 00:00:00"
//      given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
//      when("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
//      then ("It should return a DateTime value %s" format res.toString())
//      res.toString() should equal(SQLtimestamp)
//    }
//
//    scenario("POSTGRESQL: String to Datetime Nr. 3"){
//      info("test for 'today'::timestamp")
//      val SQLtimestamp = "2012-02-23 00:00:00"
//      given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
//      when("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
//      then ("It should return a DateTime value %s" format res.toString())
//      res.toString() should equal(SQLtimestamp)
//    }
//
//    scenario("POSTGRESQL: String to Datetime Nr. 4"){
//      info("test for 'tomorrow'::timestamp")
//      val SQLtimestamp = "2012-02-24 00:00:00"
//      given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
//      when("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
//      then ("It should return a DateTime value %s" format res.toString())
//      res.toString() should equal(SQLtimestamp)
//    }
//
//    scenario("POSTGRESQL: String to Datetime Nr. 5"){
//      info("test for timezone: SELECT TIMESTAMP WITH TIME ZONE 'now';")
//      val SQLtimestamp = "2012-02-23 15:49:39.466516+01"
//      given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
//      when("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
//      then ("It should return a DateTime value %s" format res.toString())
//      res.toString() should equal(SQLtimestamp)
//    }


  /*
   * POSTGRES
   */

    scenario("POSTGRESQL: String to DateTime Nr. 1"){
      info("test for SELECT 'now'::timestampTZ")
      TestDb.using{ db =>
        val timestamptz = db.row("select now()::timestampTZ"){rS => rS.get[String](1)}.get
        given(""" a starting String value for sql timestamp "%s""" format timestamptz)
        when("that value is converted to DateTime")
        and("compared with DateTime value of now %s" format DateTime.now())
        val ts1 = dF1 parseDateTime(timestamptz)
        val p = new Period(DateTime.now, ts1)
        then ("difference between these two values should be 0")
        p.getValues().init.sum should equal(0)
      }
    }

    scenario("POSTGRESQL: String to DateTime Nr. 2"){
      info("test for SELECT 'epoch'::timestampTZ")
      TestDb.using{ db =>
        val timestamptz = db.row("select 'epoch'::timestampTZ"){rS => rS.get[String](1)}.get
        given(""" a starting String value for sql timestamp "%s""" format timestamptz)
        when("that value is converted to DateTime")
        and("compared with DateTime value of epoch %s")
        val res = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ssZZ") parseDateTime(timestamptz)
        val p = new Period(new DateTime(1970,1,1,1,0,0,1), res)
        then ("difference between these two values should be %s" format res)
        p.getValues().init.sum should equal(0)
      }
    }

    scenario("POSTGRESQL: String to DateTime Nr. 3"){
      info("test for SELECT 'today'::timestampTZ")
      TestDb.using{ db =>
        val timestamptz = db.row("select 'today'::timestampTZ"){rS => rS.get[String](1)}.get
        given(""" a starting String value for sql timestamp "%s""" format timestamptz)
        when("that value is converted to DateTime")
        and("compared with DateTime value of today")
        val res = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ssZZ") parseDateTime(timestamptz)
        val comp = new DateTime(DateTime.now().toLocalDate().toString())
        val p = new Period(comp, res)
        then ("difference between these two values should be %s" format res)
        p.getValues().init.sum should equal(0)
      }
    }

    scenario("POSTGRESQL: String to DateTime Nr. 4"){
      info("test for SELECT 'tomorrow'::timestampTZ")
      TestDb.using{ db =>
        val timestamptz = db.row("select 'tomorrow'::timestampTZ"){rS => rS.get[String](1)}.get
        given(""" a starting String value for sql timestamp "%s""" format timestamptz)
        when("that value is converted to DateTime")
        val comp = new DateTime(DateTime.now().plusDays(1).toLocalDate().toString())
        and("compared with DateTime value of tomorrow %s" format comp)
        val res = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ssZZ") parseDateTime(timestamptz)
        val p = new Period(comp, res)
        then ("difference between these two values should be %s" format res)
        p.getValues().init.sum should equal(0)
      }
    }


    scenario("POSTGRESQL: DateTime test Nr. 1"){
      info("test for SELECT 'now'::timestampTZ;")
      info("I want to test if sql query will return a proper DateTime")
      TestDb.using{ db =>
        val timestamptz = db.row("select now()::timestampTZ;"){rS => rS.get[DateTime](1)}.get
        given(""" a starting DateTime value for sql timestampTZ "%s""" format timestamptz)
        when("that value is compared with DateTime %s" format DateTime.now)
        val p = new Period(DateTime.now, timestamptz)
        then ("difference between these two values should be 0" format DateTime.now())
        p.getValues().init.sum should equal(0)
      }
    }

    scenario("POSTGRESQL: DateTime test Nr. 2"){
      info("test for SELECT 'epoch'::timestampTZ;")
      info("I want to test if sql query will return a proper DateTime")
      TestDb.using{ db =>
        val timestamptz = db.row("select 'epoch'::timestampTZ + INTERVAL '1 milliseconds';"){rS => rS.get[DateTime](1)}.get
        given(""" a starting DateTime value for sql timestampTZ "%s""" format timestamptz)
        val cmp = new DateTime(1970,1,1,1,0,0,1)
        when("that value is compared with DateTime %s" format cmp)
        val p = new Period(cmp, timestamptz)
        then ("difference between these two values should be 0" format DateTime.now())
        p.getValues().sum should equal(0)
      }
    }

    scenario("POSTGRESQL: DateTime test Nr. 3"){
      info("test for SELECT 'today'::timestampTZ;")
      info("I want to test if sql query will return a proper DateTime")
      TestDb.using{ db =>
        val timestamptz = db.row("select 'today'::timestampTZ + INTERVAL '1 milliseconds';"){rS => rS.get[DateTime](1)}.get
        given(""" a starting DateTime value for sql timestampTZ "%s""" format timestamptz)
        val t = DateTime.now()
        val cmp = new DateTime(t.getYear(), t.getMonthOfYear(), t.getDayOfMonth(), 0, 0, 0, 1)
        when("that value is compared with DateTime %s" format cmp)
        val p = new Period(cmp, timestamptz)
        then ("difference between these two values should be 0" format DateTime.now())
        p.getValues().sum should equal(0)
      }
    }

    scenario("POSTGRESQL: DateTime test Nr. 4"){
      info("test for SELECT 'tomorrow'::timestampTZ;")
      info("I want to test if sql query will return a proper DateTime")
      TestDb.using{ db =>
        val timestamptz = db.row("select 'tomorrow'::timestampTZ + INTERVAL '1 milliseconds';"){rS => rS.get[DateTime](1)}.get
        given(""" a starting DateTime value for sql timestampTZ "%s""" format timestamptz)
        val t = DateTime.now().plusDays(1)
        val cmp = new DateTime(t.getYear(), t.getMonthOfYear(), t.getDayOfMonth(), 0, 0, 0, 1)
        when("that value is compared with DateTime %s" format cmp)
        val p = new Period(cmp, timestamptz)
        then ("difference between these two values should be 0" format DateTime.now())
        p.getValues().sum should equal(0)
      }
    }

  }
}
