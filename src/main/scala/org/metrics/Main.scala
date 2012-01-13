package org.metrics


object Main {

  def main(args: Array[String]): Unit = {
    val eq = new EventQueue
    val eqp = new EventQueueProcessor(eq)
    eqp.add(new PercentileGraph)
    eqp.add(new InvocationsPerMinuteGraph)
    eqp.start
    new EventSource(eq).start
    
    
  }

}