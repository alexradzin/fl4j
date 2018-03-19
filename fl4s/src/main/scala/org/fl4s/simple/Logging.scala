package org.fl4s.simple
import org.fl4j.{Log, LogBuilder}
import org.fl4j.LogLevel.{DEBUG, ERROR, TRACE, WARNING}

/**
  * Created by alexander on 3/19/18.
  */
trait Logging extends LogDelegate {
  private def eLogging: Log = LogBuilder.builder.withLevel(ERROR).build
  private def wLogging: Log = LogBuilder.builder.withLevel(WARNING).build
  private def iLogging: Log = LogBuilder.builder.build
  private def dLogging: Log = LogBuilder.builder.withLevel(DEBUG).build
  private def tLogging: Log = LogBuilder.builder.withLevel(TRACE).build


  def logError(msg: => String) {
    log(eLogging, msg)
  }

  def logWarn(msg: => String) {
    log(wLogging, msg)
  }

  def logInfo(msg: => String) {
    log(iLogging, msg)
  }

  def logDebug(msg: => String) {
    log(dLogging, msg)
  }

  def logTrace(msg: => String) {
    log(dLogging, msg)
  }
}
