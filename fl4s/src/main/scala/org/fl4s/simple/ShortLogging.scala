package org.fl4s.simple

import org.fl4j.LogLevel.{DEBUG, ERROR, TRACE, WARNING}
import org.fl4j.{Log, LogBuilder}

/**
  * Created by alexander on 3/19/18.
  */
trait ShortLogging extends LogDelegate {
  private def eShortLogging: Log = LogBuilder.builder.withLevel(ERROR).build
  private def wShortLogging: Log = LogBuilder.builder.withLevel(WARNING).build
  private def iShortLogging: Log = LogBuilder.builder.build
  private def dShortLogging: Log = LogBuilder.builder.withLevel(DEBUG).build
  private def tShortLogging: Log = LogBuilder.builder.withLevel(TRACE).build


  def error(msg: => String) {
    log(eShortLogging, msg)
  }

  def warn(msg: => String) {
    log(wShortLogging, msg)
  }

  def info(msg: => String) {
    log(iShortLogging, msg)
  }

  def debug(msg: => String) {
    log(dShortLogging, msg)
  }

  def trace(msg: => String) {
    log(dShortLogging, msg)
  }
}
