//package org.pgscala.util
//package test
//package record
//
//import org.scalatest.{FeatureSpec, GivenWhenThen}
//import org.scalatest.matchers.MustMatchers
//
//class PGRecordStringSpec extends FeatureSpec
//                    with GivenWhenThen
//                    with MustMatchers {
//  feature("Strings can be quoted into record string literals") {
//
//    val origStr = """It's OK -> \ Don"t {worry} be (happy)! /"""
//    val quotStr = """"It's OK -> \\ Don""t {worry} be (happy)! /""""
//
//    scenario("String can be quoted") {
//      PGRecord.quoteString(origStr) must equal (quotStr)
//    }
//
//    scenario("Boundary conditions must satisfy preset rules") {
//      info("The quoteString must return an empty string on null input")
//      PGRecord.quoteOptString(null: String) must equal ("")
//
//      info("""The quoteString must return "" on empty string input""")
//      PGRecord.quoteString("") must equal ("\"\"")
//    }
//
//    scenario("Strings can be quoted to be directly embedded into queries") {
//      val testSchema = "test_schema[%08x:%04x]" format(
//        System.currentTimeMillis(), scala.util.Random.nextInt()
//      )
//
//      PGTestDb.testInSchema(testSchema){
//        implicit val schema = Some(testSchema)
//
//        info("Testing in schema [%s] ..." format PGIdent.quote(testSchema))
//
//        PGTestDb.iud("""
//          CREATE TYPE oneStr AS (s text);
//        """)
//
//        PGTestDb.qry("""
//          SELECT (%s::oneStr).s;
//        """ format (
//          PGLiteral.quoteString("(%s)" format(
//            PGRecord.quoteString(origStr)
//          ))
//        )){ rS =>
//          rS.next()
//          rS.getString(1) must equal (origStr)
//        }
//      }
//    }
//  }
//}
