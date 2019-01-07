package com.scoring.query.service

import com.scoring.query.model.query.{ScoringQuery, Select}
import org.springframework.stereotype.Service

@Service
class CommandQueryModelingService extends CommandModelingService {

  val regexCommand = "(-)(s|f|o)=.*".r
  val regexSelect = "-s=.*"
  var select = "-s="
  var selectCSV = ","

  override def process(args: String*): ScoringQuery = {
    var scoringQuery: ScoringQuery = new ScoringQuery()

    for (a <- args.filter(s => regexCommand.findFirstIn(s).isDefined)) {
      if (a.matches(regexSelect)) {
        scoringQuery = select(scoringQuery, a)
      }
    }

    return scoringQuery
  }

  def select(scoringQuery: ScoringQuery, arg: String): ScoringQuery = {

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

}
