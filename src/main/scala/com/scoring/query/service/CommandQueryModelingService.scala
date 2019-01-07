package com.scoring.query.service

import com.scoring.query.model.query.ScoringQuery
import org.springframework.stereotype.Service

@Service
class CommandQueryModelingService extends CommandModelingService {

  override def process(args: String*): ScoringQuery = {
    var scoringQuery: ScoringQuery = new ScoringQuery()
    args.foreach(arg => println(arg))
    return scoringQuery
  }

}
