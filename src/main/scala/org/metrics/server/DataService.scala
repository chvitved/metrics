package org.metrics.server

import cc.spray._

trait DataService extends Directives {
  
  val dataService = {
    path("data") {
      get { _.complete(<h1>here are some data</h1>) }
    }
  }

}