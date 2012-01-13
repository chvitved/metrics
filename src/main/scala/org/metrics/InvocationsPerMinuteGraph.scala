package org.metrics
import scala.collection.mutable.Buffer
import scala.collection.mutable._
import java.util.Date

class InvocationsPerMinuteGraph extends Graph{

  override def category = "dk.medicinkortet.service.*"
  override def timeInterval = Minute.timeInMillis
  
  def add(event: Event, timeInIntervalTime: Long) {
    val value = points(timeInIntervalTime)
    setPoint(timeInIntervalTime, value + 1)    
  }
}