package org.metrics.server

import cc.spray._

trait FileService extends Directives {
  
  val fileService = {
    pathPrefix("files") {
    	get {getFromResourceDirectory("www")}
    }
  }
  
  
}