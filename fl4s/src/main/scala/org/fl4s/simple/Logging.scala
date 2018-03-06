package org.fl4s.simple

import org.fl4j.LogLevel.{DEBUG, ERROR, TRACE, WARNING}
import org.fl4j.{Log, LogBuilder}


trait Logging {
  val err: Log  = LogBuilder.builder.withLevel(ERROR).build
  val wrn: Log  = LogBuilder.builder.withLevel(WARNING).build
  val inf: Log = LogBuilder.builder.build
  val dbg: Log  = LogBuilder.builder.withLevel(DEBUG).build
  val trc: Log  = LogBuilder.builder.withLevel(TRACE).build
}
