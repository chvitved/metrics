package org.metrics.server

import akka.config.Supervision._
import akka.actor.Supervisor
import akka.actor.Actor._
import cc.spray._

class Boot {
  
  val fileModule = new FileService {
    // bake your module cake here
  }
  
  val dataModule = new DataService {
    // bake your module cake here
  }

  val fileService = actorOf(new HttpService(fileModule.fileService))
  val dataService = actorOf(new HttpService(dataModule.dataService))
  val rootService = actorOf(new RootService(fileService, dataService))

  Supervisor(
    SupervisorConfig(
      OneForOneStrategy(List(classOf[Exception]), 3, 100),
      List(
        Supervise(fileService, Permanent),
        Supervise(dataService, Permanent),
        Supervise(rootService, Permanent)
      )
    )
  )
}