package app

import app.ro.SearchRequest
import grails.converters.JSON
import org.joda.time.LocalDate

class SearchController {

  def appPipeline

  def search() {
    def searchRequest = new SearchRequest(
      location: params.location,
      checkIn: LocalDate.parse(params.checkIn),
      checkOut: LocalDate.parse(params.checkOut))

    render appPipeline.request(searchRequest) as JSON
  }
}
