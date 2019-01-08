package com.scoring.query.service

import com.scoring.query.model.ScoringModel
import com.scoring.query.model.query.ScoringQueryModel

trait QueryService {

  def process(query: ScoringQueryModel, url: String): List[ScoringModel]

}
