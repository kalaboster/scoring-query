package com.scoring.query.service

import java.io.StringWriter

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.scoring.query.model.ScoringModel
import com.scoring.query.model.query.ScoringQueryModel
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{BasicResponseHandler, HttpClientBuilder}
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class QueryScoringService extends QueryService {

  private val log = LoggerFactory.getLogger(classOf[QueryScoringService])

  override def process(scoringQuery: ScoringQueryModel, url: String): List[ScoringModel] = {

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    val out = new StringWriter
    mapper.writeValue(out, scoringQuery)

    val request = new HttpPost(url)
    request.addHeader("Content-type", "application/json")
    request.setEntity(new StringEntity(out.toString()))
    val client = HttpClientBuilder.create.build
    val response: HttpResponse = client.execute(request)

    val handler = new BasicResponseHandler
    val body = handler.handleResponse(response)
    if (!response.getStatusLine.getStatusCode.equals(200)) {
      log.error("Status " + response.getStatusLine.getStatusCode + " from url: " + url)
    }

    return mapper.readValue(body, classOf[List[ScoringModel]])
  }
}
