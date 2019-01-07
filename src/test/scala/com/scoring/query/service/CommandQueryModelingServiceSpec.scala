package com.scoring.query.service

import com.scoring.query.model.query.ScoringQuery
import org.scalatest.FlatSpec

class CommandQueryModelingServiceSpec extends FlatSpec {

  "A modeling service with args" should "return a querymodel" in {
    var commandQueryModelingService: CommandQueryModelingService = new CommandQueryModelingService
    var args = List("empty", "bad=bad=bad", "-s=STB,PROVIDER", "-f=Date:2014-09-08", "", "=", "uuu=")
    var scoringQuery: ScoringQuery = commandQueryModelingService.process()

    assert(scoringQuery != (null))

  }

}
