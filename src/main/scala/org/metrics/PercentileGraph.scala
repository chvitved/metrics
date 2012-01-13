package org.metrics
import java.util.Date
import scala.collection.mutable._

class PercentileGraph extends Graph{

  override def category = "dk.medicinkortet.service.*"
  override def timeInterval = Minute.timeInMillis
  def buffer = 3
  
  //very stupid datatypes choosen
  //redo this when poc is working
  //maybe with an indexable skiplist
  var currentTimes = HashMap[Long, Buffer[Int]]()
  
  def add(event: Event, timeInIntervalTime: Long) {
    val currentTime = timeInInterval(System.currentTimeMillis())
    if ((currentTime - timeInIntervalTime) < buffer * timeInIntervalTime) {
      calc(event, timeInIntervalTime)
    }
    cleanDataOlderThan(currentTime - (timeInterval * buffer))
  }
  
  private def calc(event: Event, timeInIntervalTime: Long) {
    currentTimes.get(timeInIntervalTime) match {
      case Some(buffer) => put(event.value, buffer)
      case None => {
        val buffer = Buffer[Int]()
        put(event.value, buffer)
        currentTimes.put(timeInIntervalTime, buffer)
      }
    }
    setPoint(timeInIntervalTime, calcMedian(currentTimes(timeInIntervalTime)))
  }
  
  private def cleanDataOlderThan(time: Long) {
    for(t <- currentTimes.keySet if (t < time)) {
      currentTimes.remove(t)
    }
  }
  
  
  private def calcMedian(numbers: Buffer[Int]): Int = {
    val sortedBuffer = numbers.sortWith((e1, e2) => (e1 < e2))
    sortedBuffer(sortedBuffer.length/2)
  }
  
  private def put(value: String, buffer: Buffer[Int]) {
    buffer += Integer.parseInt(value)
  }
}