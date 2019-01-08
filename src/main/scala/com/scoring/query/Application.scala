package com.scoring.query

import com.scoring.query.service.{CommandQueryModelingService, QueryScoringService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.{CommandLineRunner, SpringApplication}

object Application {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}

@SpringBootApplication
class Application extends CommandLineRunner {

  @Autowired
  var commandQueryService: CommandQueryModelingService = null

  @Autowired
  var queryScoringService: QueryScoringService = null

  override def run(args: String*): Unit = {
    var scoringQuery = commandQueryService.process(args: _*)
    var listScoringModel = queryScoringService.process(scoringQuery, "http://localhost:8888/api/scoring/query")
    println("\n\n##### Query Return #####")
    listScoringModel.toStream.foreach[Any](println)
    println("########################\n\n")
    sys.exit(0)
  }

}


