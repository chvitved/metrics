package org.metrics
import java.util.Date
import scala.util.Random

class EventSource(eq: EventQueue) {

  def start = new Thread() {
    override def run() {
      val random = new Random()
      while(true) {
    	  Thread.sleep(100)
    	  add(Event("dk.medicinkortet.service.hentmedicinkort", random.nextInt(2000) + "", new Date(), "machine1"))        
      }
    }
  }.start
  
  private def add(e: Event) = eq.add(e)
}