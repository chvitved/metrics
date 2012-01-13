package org.metrics

abstract sealed class Time (val timeInMillis: Long)
  case object Millisecond extends Time(1)
  case object Second extends Time(1000)
  case object Minute extends Time(Second.timeInMillis * 60)
  case object Hour extends Time(Minute.timeInMillis * 60)
  case object Day extends Time(Hour.timeInMillis * 24)