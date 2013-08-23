package org.pgscala.util
package test
package literal

import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatest.matchers.MustMatchers

class PGLiteralIntSpec extends FeatureSpec
                    with GivenWhenThen
                    with MustMatchers {
  feature("Integer can be converted into string literals") {

    scenario("Integer conversion") {
      PGLiteral.quote(-100.toString) must equal ("'-100'")
      PGLiteral.quote(0.toString) must equal ("'0'")
      PGLiteral.quote(300000.toString) must equal ("'300000'")
    }

    scenario("Integers can be quoted to be directly embedded into queries") {
      val dbTrue = PGTestDb.qry("SELECT %s, %s, %s;" format(
          PGLiteral.quote(-100.toString),
          PGLiteral.quote(0.toString),
          PGLiteral.quote(300000.toString))){ rS =>
        rS.next()
        rS.getInt(1) must be (-100)
        rS.getInt(2) must be (0)
        rS.getInt(3) must be (300000)
      }
    }

    scenario("Random generated integere must be able to make a roundabout trip") {
      val trials = 100

      import scala.util.Random
      val seed = Random.nextInt
      Random.setSeed(seed)

      Given("%d random integers" format trials)
      And("a random seed of [%d]" format seed)

      val values =
        Array.fill(trials){Random.nextInt()}

      val queryStub = """
        SELECT
          orig_int
        FROM (VALUES
          %s
        ) v(orig_int);
      """

      val quotes =
        values.map(v =>
          v -> PGLiteral.quote(v.toString)
        )

      val query =
        queryStub.format(
          quotes.map{ case (origGenInt, quotGenInt) =>
            "(%s)" format quotGenInt
          }.mkString(",\n")
        )

      When("they are quoted and embedded into a query (%d chars)" format query.length)
      Then("the returned integers must equal the original integers")

      val okCount = PGTestDb.qry(query){rS =>
        values.count{origGenInt =>
          rS.next()

          val dbOrigGenInt = rS.getInt("orig_int")
          origGenInt must equal (dbOrigGenInt)
          true
        }
      }

      info("Passed %d checks!" format okCount)
      okCount must be (values.size)
    }
  }
}
