package com.tendies.train.web.utils.http

import akka.http.scaladsl.client.RequestBuilding.Get

import scala.concurrent.duration.Duration
import scala.concurrent.Await
import com.tendies.train.web.utils.http.HttpRequestsUtils._

object RedditHttpRequests {

  def main(args: Array[String]): Unit = {
    val wallStreetBets = getSubReddit("wallstreetbets", "20")
    print(wallStreetBets)
  }

  def getSubReddit(subreddit: String, count: String) = {
    val request = Get(s"https://www.reddit.com/r/$subreddit/new/.json?count=$count")
    val response = Await.result(makeRequest(request), Duration.Inf)
    mapResponseToJsonString(response)
  }

}
