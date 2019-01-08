package com.scoring.query.service

import com.scoring.query.model.query.{Filter, Order, ScoringQueryModel, Select}
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

  override def default(): ScoringQueryModel = {
    var scoringQueryModel: ScoringQueryModel = new ScoringQueryModel

    scoringQueryModel.filter = new Filter
    scoringQueryModel.order = new Order
    scoringQueryModel.select = new Select
    scoringQueryModel.select.provider = true
    scoringQueryModel.select.viewTime = true
    scoringQueryModel.select.title = true
    scoringQueryModel.select.date = true
    scoringQueryModel.select.stb = true
    scoringQueryModel.select.rev = true

    return scoringQueryModel
  }

  override def process(args: String*): ScoringQueryModel = {
    var scoringQuery: ScoringQueryModel = new ScoringQueryModel()

    if (!args.isEmpty) {

      for (a <- args.filter(s => regexCommand.findFirstIn(s).isDefined)) {
        if (a.matches(regexSelect)) {
          select(scoringQuery, a)
        } else {
          scoringQuery.select = new Select
          scoringQuery.select.provider = true
          scoringQuery.select.viewTime = true
          scoringQuery.select.title = true
          scoringQuery.select.date = true
          scoringQuery.select.stb = true
          scoringQuery.select.rev = true
        }
        if (a.matches(regexFilter)) {
          filter(scoringQuery, a)
        } else {
          scoringQuery.filter = new Filter
        }
      }
    } else {
      scoringQuery = default()
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
