//package org.pgscala
//package converters
//package test
//
//import org.streum.configrity._
//import java.sql.{Statement, ResultSet}
//import org.postgresql.ds.PGSimpleDataSource
//import org.scalatest.{FeatureSpec, GivenWhenThen}
//import org.scalatest.matchers.MustMatchers
//import org.joda.time._
//
//class PGRecordTest extends FeatureSpec
//                        with GivenWhenThen
//                        with MustMatchers {
//
//  feature("record unpacking") {
//    scenario("can read simple record") {
//      val record = "(\"NULL\",,\"\")"
//      val items = util.PGRecord.unpack(record)
//      items(0) must be { "NULL" }
//      items(1) must be { null }
//      items(2) must be { "" }
//    }
//
//    scenario("can read simple record with NULL string value") {
//      val record = "(NULL,,\"\")"
//      val items = util.PGRecord.unpack(record)
//      items(0) must be { "NULL" }
//      items(1) must be { null }
//      items(2) must be { "" }
//    }
//
//    scenario("can unpack date") {
//      val record = "(2011-02-03)"
//      val items = util.PGRecord.unpack(record)
//      val date = PGDateConverter.fromString(items(0))
//      date must be { new LocalDate(2011, 02, 03) }
//    }
//
//    scenario("can unpack date time and int") {
//      val record = "(\"2012-01-02 20:56:15.017\",3)"
//      val items = util.PGRecord.unpack(record)
//      val time = PGNullableDateTimeConverter.fromString(items(0))
//      val number = PGIntegerConverter.fromString(items(1))
//      time.get must be { new DateTime(2012, 01, 02, 20, 56, 15, 17) }
//      number must be { 3 }
//    }
//  }
//
//  feature("record packing") {
//    scenario("can write simple record") {
//      val list = List("NULL", null, "")
//      val record = util.PGRecord.pack(list.toArray)
//      record must be { "(NULL,,\"\")" }
//    }
//
//    scenario("can pack date") {
//      val list = List(PGDateConverter.toString(new LocalDate(2011, 02, 03)))
//      val record = util.PGRecord.pack(list.toArray)
//      record must be { "(2011-02-03)" }
//    }
//
//    scenario("can pack date time and int") {
//      val list =
//        List(
//            PGNullableDateTimeConverter.toString(Some(new DateTime(2012, 01, 02, 20, 56, 15, 17))),
//            PGIntegerConverter.toString(3))
//      val record = util.PGRecord.pack(list.toArray)
//      record must be { "(2012-01-02T20:56:15.017+01:00,3)" }
//    }
//  }
//
//  feature("array packing") {
//    scenario("can write simple array") {
//      val list = List("1", null, "2")
//      val array = util.PGArray.pack(list.toArray)
//      array must be { "{1,NULL,2}" }
//    }
//
//    scenario("can escape array elements") {
//      val list = List("a\"b", null, "")
//      val array = util.PGArray.pack(list.toArray)
//      array must be { "{\"a\\\"b\",NULL,\"\"}" }
//    }
//
//  }
//}
