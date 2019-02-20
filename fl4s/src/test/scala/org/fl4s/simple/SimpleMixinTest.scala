package org.fl4s.simple

import org.fl4j.{Log, LogBuilder, LogProvider}
import org.mockito.Mockito
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSuite, Matchers}

/**
  * Created by alexander on 3/6/18.
  */
class SimpleMixinTest extends FunSuite with MockFactory with Matchers with LogHolder with Logging {
  test("Use simple inf.log") {
    val currentLog = provider()
    Mockito.when(currentLog.all("Hello, log!")).thenReturn("done")
    val r:String = inf.all("Hello, log!")
    assert(r == "done")
  }

  test("Use string replacement") {
    val something = new Something
    val currentLog = provider()
    val sinfo = s"info log ${something.get()}"
    Mockito.when(currentLog.simple(sinfo)).thenReturn("done")
    val i = inf.simple(s"info log ${something.get()}")
    assert(i == "done")
  }


  class Something {
    def get(): String = {
      println("function was called")
      "this is function"
    }
  }



  private def provider(available: Boolean = true): Log = {
    val currentLog = Mockito.mock(classOf[Log])
    LogBuilder.setLogProvider(new LogProvider {
      override def create(builder: LogBuilder): Log = currentLog
      override def isAvailable: Boolean = available
      override def getPriority = 1
    })

    currentLog
  }
}

