package org.metrics

abstract class Graph {
  
  //========To be defined by subclasses===========
  def category : String
  def timeInterval: Long
  
  def add(event: Event, timeInIntervalTime: Long)
  
  var points = Map[Long, Int]().withDefaultValue(0)
  
  def setPoint(time: Long, value: Int) {
    points = points + (time -> value)
  }
  
  //========= public methods======================  
  
  def add(event: Event) {
	val timeInIntervalTime = timeInInterval(event.time.getTime())
	add(event, timeInIntervalTime)
  }
  
  //========= private stuff=============================
  
  protected def timeInInterval(time: Long): Long = time - (time % timeInterval)

  new Thread() {
    override def run() {
      while(true) {
        Thread.sleep(1000)
        for(p <- points) {
          println(p)
        }
      }
    }
  }.start
  
}