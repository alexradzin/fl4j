package org.fl4s

import org.fl4s.simple.Logging
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by alexander on 3/6/18.
  */
class SimpleMixinTest extends FunSuite with MockFactory with Matchers with Logging {
  test("Use simple inf.log") {
    inf.all("Hello, log!")
  }

  test("Use string replacement") {
    val something = new Something
    inf.log(s"info log ${something.get()}")
    trc.log(s"info log ${something.get()}")
    logInfo(s"info log ${something.get()}")


    log.log("sssss", something)
  }



  class Something {
    def get(): String = {
      println("function was called")
      "this is function"
    }
  }

}

