package org.fl4s

/**
  * Created by alexander on 3/14/18.
  */
trait Log {
  def log(msg: => String): String = {
    msg
  }

  def log[T](msg: => String, arg1: T): T = {
    arg1
  }
}

object Log extends Log

