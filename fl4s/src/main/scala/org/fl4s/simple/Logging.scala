package org.fl4s.simple

import org.fl4j.LogLevel.{DEBUG, ERROR, TRACE, WARNING}
import org.fl4j.{Log, LogBuilder}


trait Logging {
  def err: Log = LogBuilder.builder.withLevel(ERROR).build
  def wrn: Log = LogBuilder.builder.withLevel(WARNING).build
  def inf: Log = LogBuilder.builder.build
  def dbg: Log = LogBuilder.builder.withLevel(DEBUG).build
  def trc: Log = LogBuilder.builder.withLevel(TRACE).build


  def logInfo(msg: => String) {
    if (inf.isEnabled) {
      inf.log(msg)
    }
  }


  def log: org.fl4s.Log = org.fl4s.Log


}
