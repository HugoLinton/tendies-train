package com.tendies.train.web.utils.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object HttpRequestsUtils {

  implicit val system = ActorSystem()
  implicit val ec = ExecutionContext.global

  def makeRequest(request: HttpRequest): Future[HttpResponse] = {
    Http(system).singleRequest(request)
  }

  def mapResponseToJsonString(response: HttpResponse): String = {
    Await.result(Unmarshal(response.entity).to[String], Duration.Inf)
  }
}
