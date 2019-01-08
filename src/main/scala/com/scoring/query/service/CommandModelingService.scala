package com.scoring.query.service

import com.scoring.query.model.query.ScoringQueryModel

trait CommandModelingService {

  def process(args: String*): ScoringQueryModel

  def default(): ScoringQueryModel

}
