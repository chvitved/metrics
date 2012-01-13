package org.metrics.server

import org.specs2.mutable.Specification

import cc.spray.http.HttpContent.pimpHttpContentWithAs2
import cc.spray.http.HttpMethods.GET
import cc.spray.http.HttpMethods.POST
import cc.spray.http.StatusCodes.MethodNotAllowed
import cc.spray.http.HttpRequest
import cc.spray.http.HttpResponse
import cc.spray.test.SprayTest

class FileServiceSpec extends Specification with SprayTest with FileService {
  
  "The HelloService" should {
    "return a greeting for GET requests to the root path" in {
      testService(HttpRequest(GET, "/")) {
        fileService
      }.response.content.as[String] mustEqual Right("Say hello to Spray!")
    }
    "leave GET requests to other paths unhandled" in {
      testService(HttpRequest(GET, "/kermit")) {
        fileService
      }.handled must beFalse
    }
    "return a MethodNotAllowed error for POST requests to the root path" in {
      testService(HttpRequest(POST, "/")) {
        fileService
      }.response mustEqual HttpResponse(MethodNotAllowed, "HTTP method not allowed, supported methods: GET")
    }
  }
  
}