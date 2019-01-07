package com.scoring.query.service

import com.scoring.query.model.query.ScoringQuery
import org.scalatest.FlatSpec

class CommandQueryModelingServiceSpec extends FlatSpec {

  "A modeling service with select and filter args" should "return a querymodel" in {
    var commandQueryModelingService: CommandQueryModelingService = new CommandQueryModelingService
    var args = List("empty", "bad=bad=bad", "-s=STB,PROVIDER", "-f=DATE:2014-09-08,REV:01", "", "-ff=STB", "", "-f=STB", "=", "uuu=")
    var scoringQuery: ScoringQuery = commandQueryModelingService.process(args: _*)

    assert(scoringQuery != (null))
    assert(scoringQuery.select != (null))
    assert(scoringQuery.select.stb == true)
    assert(scoringQuery.select.provider == true)
    assert(scoringQuery.select.date == false)
    assert(scoringQuery.select.rev == false)
    assert(scoringQuery.select.title == false)
    assert(scoringQuery.select.viewTime == false)
    assert(scoringQuery.filter.map.size == 2)

  }

}
