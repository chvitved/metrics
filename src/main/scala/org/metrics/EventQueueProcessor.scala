package org.metrics
import java.util.regex.Pattern

class EventQueueProcessor(val eq: EventQueue) {
  
  var graphs = List[Graph]()
  
  val thread = new Thread() {
    override def run() {
      while(true) {
        val e = take
        for (g <- graphs.filter(g => matchCategori(g.category, e.category))) {
          g.add(e)
        }
      }
    }
  }
  
  def start = thread.start
  
  def add(graph: Graph) {
    graphs = graph :: graphs
  }
  
  private def take = eq.take
  
  private def matchCategori(graphCategori: String, eventCatetory: String): Boolean = {
    val index = graphCategori.indexOf("*")
    if (index >= 0) {
    	eventCatetory.startsWith(graphCategori.substring(0, index))
    } else {
      eventCatetory.equals(graphCategori)
    }
  }

}