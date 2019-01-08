package com.scoring.query.service

import com.scoring.query.model.query.{ScoringQueryModel, Select}
import org.springframework.stereotype.Service


@Service
class CommandQueryModelingService extends CommandModelingService {

  val regexCommand = "(-)(s|f|o)=.*".r
  val regexSelect = "-s=.*"
  var select = "-s="
  var selectCSV = ","
  val regexFilter = "-f=((.*):(.*))+"
  var filter = "-f="
  var filterCSV = ","
  var filterMap = ":"

  override def process(args: String*): ScoringQueryModel = {
    var scoringQuery: ScoringQueryModel = new ScoringQueryModel()

    for (a <- args.filter(s => regexCommand.findFirstIn(s).isDefined)) {
      if (a.matches(regexSelect)) {
        select(scoringQuery, a)
      }
      if (a.matches(regexFilter)) {
        filter(scoringQuery, a)
      }
    }

    return scoringQuery
  }

  def select(scoringQuery: ScoringQueryModel, arg: String): ScoringQueryModel = {

    var selectModel: Select = new Select

    for (s <- arg.stripPrefix(select).split(selectCSV)) {
      if (s == "STB") {
        selectModel.stb = true
      }
      if (s == "PROVIDER") {
        selectModel.provider = true
      }
      if (s == "DATE") {
        selectModel.date = true
      }
      if (s == "REV") {
        selectModel.rev = true
      }
      if (s == "TITLE") {
        selectModel.title = true
      }
      if (s == "VIEWTIME") {
        selectModel.viewTime = true
      }
    }

    scoringQuery.select = selectModel

    return scoringQuery
  }

  def filter(scoringQuery: ScoringQueryModel, arg: String): ScoringQueryModel = {

    scoringQuery.filter = new com.scoring.query.model.query.Filter

    scoringQuery.filter.map = arg.stripPrefix(filter).split(filterCSV).map(_.split(filterMap)).map(arr => arr(0) -> arr(1)).toMap

    return scoringQuery
  }

}
