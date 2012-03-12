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



class DateTimeTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
//  val dFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSZZ")
//
//  feature("About to test a DateTime converter"){
//    info("I want to test if PGNullableDateTimeConverter works correctly, both in 2 way conversion")
//    info("I am going to perform tests for the DateTime special timestamp inputs")
//
//    scenario("Datetime to String Nr. 1."){
//      val t = DateTime.now()
//      given(" a starting current DateTime value %s" format t)
//      when ("that value is converted to String")
//      val res = PGNullableDateTimeConverter dateTimeToString t
//      then ("""It should return a String value "%s"""" format res)
//      res should equal(t.toString())
//    }
//
//    scenario("Datetime to String Nr. 2."){
//      val t = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss").parseDateTime("1970-01-01 00:00:00")
//      given(" a starting DateTime value of epoch %s" format t)
//      when ("that value is converted to String")
//      val res = PGNullableDateTimeConverter dateTimeToString t
//      then ("""It should return a String value "%s"""" format res)
//      res should equal(t.toString())
//    }
//
//    scenario("Datetime to String Nr. 3."){
//      val t = LocalDate.now().toDateTimeAtStartOfDay()
//      given(" a starting current DateTime value at midnight %s" format t)
//      when ("that value is converted to String")
//      val res = PGNullableDateTimeConverter dateTimeToString t
//      then ("""It should return a String value "%s"""" format res)
//      res should equal(t.toString())
//    }
//
//    scenario("Datetime to String Nr. 4."){
//      val t = DateTime.now().plus(1)
//      given(" a starting DateTime value for tomorrow %s" format t)
//      when ("that value is converted to String")
//      val res = PGNullableDateTimeConverter dateTimeToString t
//      then ("""It should return a String value "%s"""" format res)
//      res should equal(t.toString())
//    }
//
//    scenario("Datetime to String Nr. 5."){
//      val t = DateTime.now().minus(1)
//      given(" a starting DateTime value for yesterday %s" format t)
//      when ("that value is converted to String")
//      val res = PGNullableDateTimeConverter dateTimeToString t
//      then ("""It should return a String value "%s"""" format res)
//      res should equal(t.toString())
//    }
//
//    scenario("String to DateTime Nr. 1"){
//      val t = DateTime.now()
//      given(" a starting String value of current DateTime %s" format t)
//      when ("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
//      then ("It should return a DateTime value %s" format res.toString())
//      res should equal(t)
//    }
//
//    scenario("String to DateTime Nr. 2"){
//      val t = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss").parseDateTime("1970-01-01 00:00:00")
//      given(""" a starting String value of epoch "%s"""" format t)
//      when ("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
//      then ("It should return a DateTime value %s" format res.toString())
//      res should equal(t)
//    }
//
//    scenario("String to DateTime Nr. 3"){
//      val t = LocalDate.now().toDateTimeAtStartOfDay
//      given(""" a starting String value of current date at midnight "%s""" format t)
//      when ("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
//      then ("It should return a DateTime value %s" format res.toString())
//      res should equal(t)
//    }
//
//    scenario("String to DateTime Nr. 4"){
//      val t = DateTime.now().plus(1)
//      given(""" a starting String value of tomorrow's date and time "%s""" format t)
//      when ("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
//      then ("It should return a DateTime value %s" format res.toString())
//      res should equal(t)
//    }
//
//    scenario("String to DateTime Nr. 5"){
//      val t = DateTime.now().minus(1)
//      given(""" a starting String value of yesterday's date and time "%s""" format t)
//      when ("that value is converted to DateTime")
//      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
//      then ("It should return a DateTime value %s" format res.toString())
//      res should equal(t)
//    }
//
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

    scenario("POSTGRESQL: String to DateTime Nr. 1"){
      info("test for SELECT 'now'::timestamp")
      TestDb.using{ db =>
        val timestamp = db.row("select now()::timestamp"){rS => rS.get[String](1)}.get
        given(""" a starting String value for sql timestamp "%s""" format timestamp)
        when("that value is converted to DateTime")
        val ts1 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSS") parseDateTime(timestamp) getMinuteOfDay
        val ts2 = DateTime.now().getMinuteOfDay
        then ("It should return a DateTime value %s" format DateTime.now())
        (ts2-ts1) should equal(0)
      }
    }


    scenario("POSTGRESQL: DateTime to String Nr. 1"){
      info("test for timezone: SELECT TIMESTAMP WITH TIME ZONE 'now';")
      TestDb.using{ db =>
        val timestamp = db.row("select now()::timestamp"){rS => rS.get[DateTime](1)}.get
        given(""" a starting String value for sql timestamp "%s""" format timestamp)
        when("that value is converted to DateTime")
        val ts1 = timestamp.getMinuteOfDay.toString()
        then ("It should return a DateTime value %s" format DateTime.now())
        ts1 should equal(DateTime.now().getMinuteOfDay().toString())
      }
    }



}
