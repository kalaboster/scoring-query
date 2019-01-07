package com.scoring.query

import com.scoring.query.service.CommandQueryModelingService
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

  override def run(args: String*): Unit = {
    var scoringQuery = commandQueryService.process(args: _*)
    sys.exit(0)
  }


}


