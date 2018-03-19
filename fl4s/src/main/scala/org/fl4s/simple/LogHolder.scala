package org.fl4s.simple

import org.fl4j.LogLevel.{DEBUG, ERROR, TRACE, WARNING}
import org.fl4j.{Log, LogBuilder}


trait LogHolder {
  def err: Log = LogBuilder.builder.withLevel(ERROR).build
  def wrn: Log = LogBuilder.builder.withLevel(WARNING).build
  def inf: Log = LogBuilder.builder.build
  def dbg: Log = LogBuilder.builder.withLevel(DEBUG).build
  def trc: Log = LogBuilder.builder.withLevel(TRACE).build
}
