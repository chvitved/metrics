package org.metrics
import java.util.concurrent.LinkedBlockingDeque

class EventQueue {
  
  val queue = new LinkedBlockingDeque[Event]
  
  def add(e: Event) = queue.put(e)
  
  def take = queue.take
  

}