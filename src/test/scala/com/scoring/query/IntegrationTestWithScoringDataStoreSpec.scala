package com.scoring.query

import com.scoring.query.model.ScoringModel
import com.scoring.query.model.query.ScoringQueryModel
import com.scoring.query.service.{CommandQueryModelingService, QueryScoringService}
import org.scalatest.FlatSpec

class IntegrationTestWithScoringDataStoreSpec extends FlatSpec {

  "Given scoring-database is available then the test" should "return 200 and a scoring return" in {

    var commandQueryModelingService: CommandQueryModelingService = new CommandQueryModelingService
    var queryScoringService: QueryScoringService = new QueryScoringService

    var args = List("empty", "bad=bad=bad", "-s=STB,PROVIDER,DATE,", "-f=DATE:2014-04-02,STB:stb3", "", "-ff=STB", "", "-f=STB", "=", "uuu=")

    var scoringQuery: ScoringQueryModel = commandQueryModelingService.process(args: _*)

    var listScoringModel: List[ScoringModel] = queryScoringService.process(scoringQuery, "http://localhost:9090/api/scoring/query")

    assert(listScoringModel.length.equals(1))
  }

}
