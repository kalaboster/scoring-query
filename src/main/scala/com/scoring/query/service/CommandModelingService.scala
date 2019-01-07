package com.scoring.query.service

import com.scoring.query.model.query.ScoringQuery

trait CommandModelingService {

  def process(args: String*): ScoringQuery

}
