package org.fl4s.simple

import org.fl4j.Log

/**
  * Created by alexander on 3/19/18.
  */
private[simple] trait LogDelegate {
  protected[simple] def log(log: Log, msg: => String) {
    if (log.isEnabled) log.simple(msg)
  }

}
