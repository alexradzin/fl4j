package org.fl4s

import org.fl4s.simple.Logging
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by alexander on 3/6/18.
  */
class SimpleMixinTest extends FunSuite with MockFactory with Matchers with Logging {
  test("Use simple inf.log") {
    inf.log("Hello, log!")
  }
}

