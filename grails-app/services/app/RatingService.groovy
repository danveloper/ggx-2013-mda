package app

import app.ro.SearchRequest
import grails.transaction.Transactional
import org.joda.time.LocalDate

@Transactional
class RatingService {
  enum DayOfWeekFactor {
    SUN(1.25), MON(1), TUE(1), WED(1.5), THUR(1.25), FRI(1), SAT(2.25)

    BigDecimal rateScale

    DayOfWeekFactor(BigDecimal rateScale) {
      this.rateScale = rateScale
    }

    public static DayOfWeekFactor fromOrdinal(int ordinal) {
      if (ordinal > values().size()) {
        throw new UnsupportedOperationException("request ordinal is outside the enumerated bounds")
      }
      values()[ordinal]
    }
  }

  PublishedRoom rate(Room room, LocalDate checkIn, LocalDate checkOut) {
    List<BigDecimal> rates = (checkIn..checkOut).collect {
      room.baseRate * DayOfWeekFactor.fromOrdinal(it.dayOfWeek-1).rateScale
    }
    def avg = rates.sum() / rates.size()
    new PublishedRoom(baseRoom: room, rate: avg, checkIn: checkIn, checkOut: checkOut)
  }

  SearchRequest doRate(SearchRequest searchRequest) {
    searchRequest.publishedRooms = searchRequest.rooms.collect { rate(it, searchRequest.checkIn, searchRequest.checkIn) }
    searchRequest
  }

  SearchRequest doSomethingElse(SearchRequest searchRequest) {
    // do something else
  }
}
